package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Agente;

import javax.persistence.*;
import java.util.List;

public class AgenteDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(Agente agente) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(agente);
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

    public Agente get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT agente FROM Agente agente WHERE agente.id = :agenteID";

        TypedQuery<Agente> typedQuery = manager.createQuery(query, Agente.class);
        typedQuery.setParameter("agenteID", id);

        Agente agente = null;

        try {
            agente = typedQuery.getSingleResult();

            return agente;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Agente> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT agente FROM Agente agente WHERE agente.id IS NOT NULL";

        TypedQuery<Agente> typedQuery = manager.createQuery(query, Agente.class);
        List<Agente> agentes;

        try {
            agentes = typedQuery.getResultList();

            return agentes;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, String cnpj, int usuarioID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Agente agente = manager.find(Agente.class, id);
            agente.setId(id);
            agente.setCnpj(cnpj);
            agente.setUsuarioID(usuarioID);

            manager.persist(agente);
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
            Agente agente = manager.find(Agente.class, id);

            manager.remove(agente);
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
