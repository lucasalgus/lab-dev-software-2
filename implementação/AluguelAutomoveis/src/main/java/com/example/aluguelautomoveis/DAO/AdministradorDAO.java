package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Administrador;

import javax.persistence.*;
import java.util.List;

public class AdministradorDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(Administrador administrador) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(administrador);
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

    public Administrador get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT administrador FROM Administrador administrador WHERE administrador.id = :administradorID";

        TypedQuery<Administrador> typedQuery = manager.createQuery(query, Administrador.class);
        typedQuery.setParameter("administradorID", id);

        Administrador administrador = null;

        try {
            administrador = typedQuery.getSingleResult();

            return administrador;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Administrador> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT administrador FROM Administrador administrador WHERE administrador.id IS NOT NULL";

        TypedQuery<Administrador> typedQuery = manager.createQuery(query, Administrador.class);
        List<Administrador> administradores;

        try {
            administradores = typedQuery.getResultList();

            return administradores;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, int usuarioID) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Administrador administrador = manager.find(Administrador.class, id);
            administrador.setId(id);
            administrador.setUsuarioID(usuarioID);

            manager.persist(administrador);
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
            Administrador administrador = manager.find(Administrador.class, id);

            manager.remove(administrador);
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
