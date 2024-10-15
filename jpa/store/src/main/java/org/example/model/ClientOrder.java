package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client_order")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price = BigDecimal.ZERO;
    private LocalDate creation_date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(mappedBy = "clientOrder", cascade = CascadeType.ALL)
    private List<ItemOrder> itemOrderList = new ArrayList<>();

    public ClientOrder(){}

    public ClientOrder(Client client) {
        this.client = client;
    }

    public void addItem(ItemOrder itemOrder) {
        itemOrder.setOrder(this);
        this.itemOrderList.add(itemOrder);
        this.price = this.price.add(itemOrder.getValue());
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

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
