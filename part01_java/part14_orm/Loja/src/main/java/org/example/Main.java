package org.example;

import jakarta.persistence.EntityManager;
import org.example.daoimpl.CategoryDAOImpl;
import org.example.daoimpl.ProductDAOImpl;
import org.example.model.Category;
import org.example.model.Product;
import org.example.util.JPAUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        registerNewProduct();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAOImpl productDAO = new ProductDAOImpl(em);

        Product product = productDAO.searchById(1);
        System.out.println(product);

        productDAO.searchAll().forEach(System.out::println);
    }

    private static void registerNewProduct() {
        Category cPhone = new Category("PHONE");
        Product phone = new Product("A30", "Celular", new BigDecimal("990.35"), cPhone);

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl(entityManager);
        ProductDAOImpl productDAO = new ProductDAOImpl(entityManager);

        entityManager.getTransaction().begin();
        categoryDAO.register(cPhone);
        productDAO.register(phone);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
}