package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.CreneauDAO;
import ma.youcode.model.Creneau;

public class CreneauDAOImpl implements CreneauDAO {

    @Override
    public Creneau save(Creneau creneau) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(creneau);
            tx.commit();
            return creneau;
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
    public Optional<Creneau> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Creneau creneau = em.createQuery(
                    "SELECT c FROM Creneau c " +
                            "LEFT JOIN FETCH c.medecinSpecialiste " +
                            "WHERE c.id = :id",
                    Creneau.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(creneau);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public Creneau update(Creneau creneau) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Creneau updatedCreneau = em.merge(creneau);
            tx.commit();
            return updatedCreneau;
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
    public void delete(Creneau creneau) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Creneau managedCreneau = em.contains(creneau) ? creneau : em.merge(creneau);
            em.remove(managedCreneau);
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
    public List<Creneau> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Creneau c " +
                            "LEFT JOIN FETCH c.medecinSpecialiste",
                    Creneau.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}