package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Empregador;

import javax.persistence.*;
import java.util.List;

public class EmpregadorDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(Empregador empregador) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(empregador);
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

    public Empregador get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT empregador FROM Empregador empregador WHERE empregador.id = :empregadorID";

        TypedQuery<Empregador> typedQuery = manager.createQuery(query, Empregador.class);
        typedQuery.setParameter("empregadorID", id);

        Empregador empregador = null;

        try {
            empregador = typedQuery.getSingleResult();

            return empregador;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Empregador> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT empregador FROM Empregador empregador WHERE empregador.id IS NOT NULL";

        TypedQuery<Empregador> typedQuery = manager.createQuery(query, Empregador.class);
        List<Empregador> empregadores;

        try {
            empregadores = typedQuery.getResultList();

            return empregadores;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, String nome, int clienteID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Empregador empregador = manager.find(Empregador.class, id);
            empregador.setId(id);
            empregador.setNome(nome);
            empregador.setClienteID(clienteID);

            manager.persist(empregador);
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
            Empregador empregador = manager.find(Empregador.class, id);

            manager.remove(empregador);
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
