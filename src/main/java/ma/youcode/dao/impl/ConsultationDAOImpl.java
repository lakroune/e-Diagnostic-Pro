package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.ConsultationDAO;
import ma.youcode.model.Consultation;

public class ConsultationDAOImpl implements ConsultationDAO {

    @Override
    public Consultation save(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(consultation);
            tx.commit();
            return consultation;
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
    public Optional<Consultation> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            Consultation consultation = em.createQuery(
                    "SELECT c FROM Consultation c " +
                            "LEFT JOIN FETCH c.patient " +
                            "LEFT JOIN FETCH c.medecinGeneraliste " +
                            "LEFT JOIN FETCH c.actesTechniques " +
                            "WHERE c.id = :id",
                    Consultation.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(consultation);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
    @Override
    public Consultation update(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Consultation updatedConsultation = em.merge(consultation);
            tx.commit();
            return updatedConsultation;
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
    public boolean delete(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            em.remove(consultation);
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
    public List<Consultation> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT c FROM Consultation c " +
                            "LEFT JOIN FETCH c.patient " +
                            "LEFT JOIN FETCH c.medecinGeneraliste",
                    Consultation.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}