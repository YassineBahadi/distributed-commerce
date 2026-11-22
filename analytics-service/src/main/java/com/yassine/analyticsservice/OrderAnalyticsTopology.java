package com.yassine.analyticsservice;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

/**
@author pc
**/
@Configuration
@EnableKafkaStreams
public class OrderAnalyticsTopology {

    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public KStream<String, String> processOrders(StreamsBuilder builder) {

        KStream<String, String> stream =
                builder.stream("orders");

        KTable<Windowed<String>, OrderStats> statistics =
                stream
                        .groupBy(
                                (key, value) -> "all-orders",
                                Grouped.with(Serdes.String(), Serdes.String())
                        )
                        .windowedBy(
                                TimeWindows.ofSizeWithNoGrace(
                                        Duration.ofSeconds(5)
                                )
                        )
                        .aggregate(
                                OrderStats::new,
                                (key, json, stats) -> {
                                    try {
                                        JsonNode order = mapper.readTree(json);

                                        double amount = 0;

                                        for (JsonNode line : order.get("lines")) {
                                            double lineAmount =
                                                    line.get("quantity").asDouble()
                                                            * line.get("unitPrice").asDouble()
                                                            - line.get("discount").asDouble();

                                            amount += lineAmount;
                                        }

                                        stats.setCount(stats.getCount() + 1);
                                        stats.setTotalAmount(
                                                stats.getTotalAmount() + amount
                                        );

                                        return stats;

                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                },
                                Materialized.with(
                                        Serdes.String(),
                                        Serdes.serdeFrom(
                                                (topic, stats) -> {
                                                    try {
                                                        return mapper.writeValueAsBytes(stats);
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                },
                                                (topic, bytes) -> {
                                                    try {
                                                        return mapper.readValue(
                                                                bytes, OrderStats.class
                                                        );
                                                    } catch (Exception e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                        )
                                )
                        );

        statistics
                .toStream()
                .map((window, stats) -> {
                    try {
                        String json = mapper.writeValueAsString(stats);
                        return KeyValue.pair(window.key(), json);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .to(
                        "orders-analytics",
                        Produced.with(Serdes.String(), Serdes.String())
                );

        return stream;
    }
}
