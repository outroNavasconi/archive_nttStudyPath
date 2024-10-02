package org.example.daoimpl;

import jakarta.persistence.EntityManager;
import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final EntityManager em;

    public ProductDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void register(Product product) {
        this.em.persist(product);
    }

    @Override
    public void update(Product product) {
        this.em.merge(product);
    }

    @Override
    public void remove(Product product) {
        product = this.em.merge(product);
        this.em.remove(product);
    }

    @Override
    public Product searchById(long id) {
        return this.em.find(Product.class, id);
    }

    @Override
    public List<Product> searchAll() {
        String jpql = "SELECT p FROM Product p";
        return this.em.createQuery(jpql, Product.class).getResultList();
    }

    @Override
    public List<Product> searchByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :n";
        return this.em.createQuery(jpql, Product.class)
                .setParameter("nome", name)
                .getResultList();
    }

    @Override
    public List<Product> searchByCategoryName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.Category.nome = :n";
        return this.em.createQuery(jpql, Product.class)
                .setParameter("n", name)
                .getResultList();
    }

    @Override
    public BigDecimal getProductPrice(long id) {
        String jpql = "SELECT p.price FROM Product p WHERE p.id = :i";
        return this.em.createQuery(jpql, BigDecimal.class)
                .setParameter("i", id)
                .getSingleResult();
    }
}
