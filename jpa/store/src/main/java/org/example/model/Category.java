package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @EmbeddedId
    private CategoryId id;

    public Category() {}

    public Category(String type, String name) {
        this.id = new CategoryId(type, name);
    }

    public CategoryId getId() {
        return id;
    }
}
