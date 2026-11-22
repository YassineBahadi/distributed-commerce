package com.yassine.inventoryservice.command.events;

/**
 * @author pc
 **/
public class CategoryCreatedEvent {

    private String categoryId;
    private String name;
    private String description;

    // Constructeur nécessaire à la désérialisation
    public CategoryCreatedEvent() {
    }

    public CategoryCreatedEvent(
            String categoryId,
            String name,
            String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}