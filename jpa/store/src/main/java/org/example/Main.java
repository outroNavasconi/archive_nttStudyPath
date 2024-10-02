package org.example;

import jakarta.persistence.EntityManager;
import org.example.daoimpl.CategoryDAOImpl;
import org.example.daoimpl.ClientDAOImpl;
import org.example.daoimpl.OrderDAOImpl;
import org.example.daoimpl.ProductDAOImpl;
import org.example.model.*;
import org.example.util.JPAUtil;
import org.example.vo.SalesReportVo;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        registerNewProduct();
        registerNewOrder();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDAOImpl productDAO = new ProductDAOImpl(em);

        Product product = productDAO.searchById(1);
        System.out.println(product);

        productDAO.searchAll().forEach(System.out::println);
        em.close();
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
    }

    private static void registerNewOrder() {
        EntityManager em = JPAUtil.getEntityManager();
        ProductDAOImpl productDAO = new ProductDAOImpl(em);
        Product product = productDAO.searchById(1L);

        Client client = new Client("Fulano", "123456");
        ClientOrder clientOrder = new ClientOrder(client);
        clientOrder.addItem(new ItemOrder(10, clientOrder, product, product.getPrice()));

        OrderDAOImpl orderDao = new OrderDAOImpl(em);
        ClientDAOImpl clientDAO = new ClientDAOImpl(em);

        em.getTransaction().begin();
        clientDAO.register(client);
        orderDao.register(clientOrder);
        em.getTransaction().commit();

        BigDecimal total = orderDao.getTotalValue();
        System.out.println(">>>> total: " + total);

        List<Object[]> report = orderDao.salesReport();
        report.forEach(o -> System.out.println(o[0] + " | " + o[1] + " | " + o[2]));

        List<SalesReportVo> reportV2 = orderDao.salesReportV2();
        reportV2.forEach(System.out::println);
    }
}