package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Cliente;

import javax.persistence.*;
import java.util.List;

public class ClienteDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(String rg, String cpf, String profissao, int usuarioID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Cliente cliente = new Cliente(null, rg, cpf, profissao, usuarioID);

            manager.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public Cliente get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT cliente FROM Cliente cliente WHERE cliente.id = :clienteID";

        TypedQuery<Cliente> typedQuery = manager.createQuery(query, Cliente.class);
        typedQuery.setParameter("clienteID", id);

        Cliente cliente = null;

        try {
            cliente = typedQuery.getSingleResult();

            return cliente;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Cliente> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT cliente FROM Cliente cliente WHERE cliente.id IS NOT NULL";

        TypedQuery<Cliente> typedQuery = manager.createQuery(query, Cliente.class);
        List<Cliente> clientes;

        try {
            clientes = typedQuery.getResultList();

            return clientes;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, String rg, String cpf, String profissao, int usuarioID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Cliente cliente = manager.find(Cliente.class, id);
            cliente.setId(id);
            cliente.setRg(rg);
            cliente.setCpf(cpf);
            cliente.setProfissao(profissao);
            cliente.setUsuarioID(usuarioID);

            manager.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public void delete(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Cliente cliente = manager.find(Cliente.class, id);

            manager.remove(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}
