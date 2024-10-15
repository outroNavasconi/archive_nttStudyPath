package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="ItemOrder")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private int quantity;

    @ManyToOne
    private ClientOrder clientOrder;

    @ManyToOne
    private Product product;

    public ItemOrder(){}

    public ItemOrder(int quantity, ClientOrder clientOrder, Product product, BigDecimal price) {
        this.quantity = quantity;
        this.clientOrder = clientOrder;
        this.product = product;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ClientOrder getOrder() {
        return clientOrder;
    }

    public void setOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getValue() {
        return this.price.multiply(new BigDecimal(this.quantity));
    }
}
