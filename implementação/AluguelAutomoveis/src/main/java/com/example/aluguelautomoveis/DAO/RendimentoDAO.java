package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Rendimento;

import javax.persistence.*;
import java.util.List;

public class RendimentoDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(Rendimento rendimento) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(rendimento);
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

    public Rendimento get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT rendimento FROM Rendimento rendimento WHERE rendimento.id = :rendimentoID";

        TypedQuery<Rendimento> typedQuery = manager.createQuery(query, Rendimento.class);
        typedQuery.setParameter("rendimentoID", id);

        Rendimento rendimento = null;

        try {
            rendimento = typedQuery.getSingleResult();

            return rendimento;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Rendimento> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT rendimento FROM Rendimento rendimento WHERE rendimento.id IS NOT NULL";

        TypedQuery<Rendimento> typedQuery = manager.createQuery(query, Rendimento.class);
        List<Rendimento> rendimentos;

        try {
            rendimentos = typedQuery.getResultList();

            return rendimentos;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, double valor, int clienteID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Rendimento rendimento = manager.find(Rendimento.class, id);
            rendimento.setId(id);
            rendimento.setValor(valor);
            rendimento.setClienteID(clienteID);

            manager.persist(rendimento);
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
            Rendimento rendimento = manager.find(Rendimento.class, id);

            manager.remove(rendimento);
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
