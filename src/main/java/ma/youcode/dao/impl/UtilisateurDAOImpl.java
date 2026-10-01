package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.UtilisateurDAO;
import ma.youcode.model.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(utilisateur);
            tx.commit();
            return utilisateur;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Utilisateur utilisateur = em.find(Utilisateur.class, id);
            return Optional.ofNullable(utilisateur);
        } finally {
            em.close();
        }
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Utilisateur updatedUser = em.merge(utilisateur);
            tx.commit();
            return updatedUser;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Utilisateur managedUser = em.contains(utilisateur) ? utilisateur : em.merge(utilisateur);
            em.remove(managedUser);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Utilisateur> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}