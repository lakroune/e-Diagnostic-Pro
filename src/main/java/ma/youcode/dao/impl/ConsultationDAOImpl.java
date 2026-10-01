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
///      12&2
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
    public void delete(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Consultation managedConsultation = em.contains(consultation)
                    ? consultation
                    : em.merge(consultation);
            em.remove(managedConsultation);
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