package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="Client")
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalData personalData;

    public Client() {}

    public Client(String name, String cpf) {
        this.personalData = new PersonalData(name, cpf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return this.personalData;
    }
}
