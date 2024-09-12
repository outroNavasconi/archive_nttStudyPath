package org.example.dao;

import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {
    void register(Product product);
    void update(Product product);
    void remove(Product product);
    Product searchById(long id);
    List<Product> searchAll();
    List<Product> searchByName(String name);
    List<Product> searchByCategoryName(String name);
    BigDecimal getProductPrice(long id);
}
