package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Aluguel;

import javax.persistence.*;
import java.util.List;

public class AluguelDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(String contrato, int proprietarioID, int agenteID, int automovelID, int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Aluguel aluguel = new Aluguel(id, contrato, proprietarioID, agenteID, automovelID);

            manager.persist(aluguel);
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

    public Aluguel get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT cliente FROM Cliente cliente WHERE cliente.id = :clienteID";

        TypedQuery<Aluguel> typedQuery = manager.createQuery(query, Aluguel.class);
        typedQuery.setParameter("clienteID", id);

        Aluguel aluguel = null;

        try {
            aluguel = typedQuery.getSingleResult();

            return aluguel;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Aluguel> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT aluguel FROM Aluguel aluguel WHERE aluguel.id IS NOT NULL";

        TypedQuery<Aluguel> typedQuery = manager.createQuery(query, Aluguel.class);
        List<Aluguel> alugueis;

        try {
            alugueis = typedQuery.getResultList();

            return alugueis;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, String contrato, int proprietarioID, int agenteID, int automovelID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Aluguel aluguel = manager.find(Aluguel.class, id);
            aluguel.setId(id);
            aluguel.setContrato(contrato);
            aluguel.setProprietarioID(proprietarioID);
            aluguel.setAgenteID(agenteID);
            aluguel.setAutomovelID(automovelID);

            manager.persist(aluguel);
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
            Aluguel aluguel = manager.find(Aluguel.class, id);

            manager.remove(aluguel);
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
