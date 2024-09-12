package org.example.dao;

import org.example.model.Category;

public interface CategoryDAO {
    void register(Category category);
    void update(Category category);
}
