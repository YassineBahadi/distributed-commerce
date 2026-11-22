package com.yassine.analyticsservice;

/**
 * @author pc
 **/
public class OrderStats {

    private long count;
    private double totalAmount;

    public OrderStats() {
    }

    public OrderStats(long count, double totalAmount) {
        this.count = count;
        this.totalAmount = totalAmount;
    }

    public long getCount() {
        return count;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}