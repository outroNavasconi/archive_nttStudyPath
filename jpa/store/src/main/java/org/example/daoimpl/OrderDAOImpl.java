package org.example.daoimpl;

import jakarta.persistence.EntityManager;
import org.example.dao.OrderDAO;
import org.example.model.ClientOrder;
import org.example.vo.SalesReportVo;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final EntityManager em;

    public OrderDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void register(ClientOrder clientOrder) {
        this.em.persist(clientOrder);
    }

    public BigDecimal getTotalValue() {
        String jpql = "SELECT SUM(c.price) FROM ClientOrder c";
        return this.em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    @Override
    public List<Object[]> salesReport() {
        String jpql = "SELECT p.name, SUM(i.quantity) as qtd, MAX(c.creation_date) " +
                "FROM ClientOrder c " +
                "JOIN c.itemOrderList i " +
                "JOIN i.product p " +
                "GROUP BY p.name " +
                "ORDER BY qtd DESC";
        return this.em.createQuery(jpql, Object[].class)
                .getResultList();
    }

    @Override
    public List<SalesReportVo> salesReportV2() {
        String jpql = "SELECT new SalesReportVo(p.name, SUM(i.quantity), MAX(c.creation_date)) " +
                "FROM ClientOrder c " +
                "JOIN c.itemOrderList i " +
                "JOIN i.product p " +
                "GROUP BY p.name " +
                "ORDER BY i.quantity DESC";
        return this.em.createQuery(jpql, SalesReportVo.class)
                .getResultList();
    }
}
