package org.example.daoimpl;

import jakarta.persistence.EntityManager;
import org.example.dao.ClientDAO;
import org.example.model.Client;

public class ClientDAOImpl implements ClientDAO {
    private final EntityManager em;

    public ClientDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void register(Client client) {
        this.em.persist(client);
    }
}
