package com.example.aluguelautomoveis.DAO;

import com.example.aluguelautomoveis.Model.Usuario;

import javax.persistence.*;
import java.util.List;

public class UsuarioDAO {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("AluguelAutomoveis");


    public void add(String endereco, String nome, String email, String senha) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Usuario usuario = new Usuario(null, endereco, nome, email, senha);

            manager.persist(usuario);
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

    public Usuario get(int id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT usuario FROM Usuario usuario WHERE usuario.id = :userID";

        TypedQuery<Usuario> typedQuery = manager.createQuery(query, Usuario.class);
        typedQuery.setParameter("userID", id);

        Usuario usuario = null;

        try {
            usuario = typedQuery.getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public List<Usuario> getAll() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT usuario FROM Usuario usuario WHERE usuario.id IS NOT NULL";

        TypedQuery<Usuario> typedQuery = manager.createQuery(query, Usuario.class);
        List<Usuario> usuarios;

        try {
            usuarios = typedQuery.getResultList();

            return usuarios;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    public void update(int id, String endereco, String nome, String email, String senha) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Usuario usuario = manager.find(Usuario.class, id);
            usuario.setEndereco(endereco);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            manager.persist(usuario);
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
            Usuario usuario = manager.find(Usuario.class, id);

            manager.remove(usuario);
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
