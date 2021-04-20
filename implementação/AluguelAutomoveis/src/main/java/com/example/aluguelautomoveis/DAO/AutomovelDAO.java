package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Automovel;

import javax.persistence.*;
import java.util.List;

public class AutomovelDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AutomovelAutomoveis");


    public void add(int matricula, int ano, String marca, String modelo, String placa) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Automovel automovel = new Automovel(null, matricula, ano, marca, modelo, placa);

            manager.persist(automovel);
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

    public Automovel get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT automovel FROM Automovel automovel WHERE automovel.id = :automovelID";

        TypedQuery<Automovel> typedQuery = manager.createQuery(query, Automovel.class);
        typedQuery.setParameter("automovelID", id);

        Automovel Automovel = null;

        try {
            Automovel = typedQuery.getSingleResult();

            return Automovel;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Automovel> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT automovel FROM Automovel automovel WHERE automovel.id IS NOT NULL";

        TypedQuery<Automovel> typedQuery = manager.createQuery(query, Automovel.class);
        List<Automovel> automoveis;

        try {
            automoveis = typedQuery.getResultList();

            return automoveis;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, int matricula, int ano, String marca, String modelo, String placa) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Automovel automovel = manager.find(Automovel.class, id);
            automovel.setId(id);
            automovel.setMatricula(matricula);
            automovel.setAno(ano);
            automovel.setMarca(marca);
            automovel.setModelo(modelo);
            automovel.setPlaca(placa);

            manager.persist(automovel);
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
            Automovel automovel = manager.find(Automovel.class, id);

            manager.remove(automovel);
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
