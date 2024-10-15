package org.example.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CategoryId implements Serializable {
    private String type;
    private String name;

    public CategoryId(){}

    public CategoryId(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
