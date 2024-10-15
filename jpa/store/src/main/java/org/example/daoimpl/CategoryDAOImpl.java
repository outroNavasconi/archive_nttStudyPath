package org.example.daoimpl;

import jakarta.persistence.EntityManager;
import org.example.dao.CategoryDAO;
import org.example.model.Category;

public class CategoryDAOImpl implements CategoryDAO {
    private final EntityManager em;

    public CategoryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void register(Category category) {
        this.em.persist(category);
    }

    @Override
    public void update(Category category) {
        this.em.merge(category);
    }
}
