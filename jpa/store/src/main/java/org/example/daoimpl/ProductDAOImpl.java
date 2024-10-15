package org.example.daoimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public List<Product> searchByParams(String name, BigDecimal price, LocalDate creationDate) {
        String jpql = "SELECT p FROM Product p WHERE 1=1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " AND p.name = :name ";
        }

        if (price != null) {
            jpql += " AND p.price = :price ";
        }

        if (creationDate != null) {
            jpql += " AND p.creation_date = :creationDate ";
        }

        TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (price != null) {
            query.setParameter("price", price);
        }

        if (creationDate != null) {
            query.setParameter("creationDate", creationDate);
        }

        return query.getResultList();
    }

    public List<Product> searchByParamsWithCriteria(String name, BigDecimal price, LocalDate creationDate) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filters = builder.and();

        if (name != null && !name.trim().isEmpty()) {
            filters = builder.and(filters, builder.equal(from.get("name"), name));
        }

        if (price != null) {
            filters = builder.and(filters, builder.equal(from.get("price"), price));
        }

        if (creationDate != null) {
            filters = builder.and(filters, builder.equal(from.get("creation_date"), creationDate));
        }

        query.where(filters);

        return this.em.createQuery(query).getResultList();
    }
}
