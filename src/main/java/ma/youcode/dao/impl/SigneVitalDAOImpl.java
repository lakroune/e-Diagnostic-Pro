package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.SigneVitalDAO;
import ma.youcode.model.SigneVital;

public class SigneVitalDAOImpl implements SigneVitalDAO {

    @Override
    public SigneVital save(SigneVital signeVital) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(signeVital);
            tx.commit();
            return signeVital;
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
    public Optional<SigneVital> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            SigneVital signeVital = em.createQuery(
                    "SELECT s FROM SigneVital s " +
                            "LEFT JOIN FETCH s.patient " +
                            "LEFT JOIN FETCH s.infirmier " +
                            "WHERE s.id = :id",
                    SigneVital.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(signeVital);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public SigneVital update(SigneVital signeVital) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            SigneVital updatedSigne = em.merge(signeVital);
            tx.commit();
            return updatedSigne;
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
    public boolean delete(SigneVital signeVital) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(signeVital);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<SigneVital> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT s FROM SigneVital s " +
                            "LEFT JOIN FETCH s.patient " +
                            "LEFT JOIN FETCH s.infirmier " +
                            "ORDER BY s.datePrise DESC",
                    SigneVital.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}