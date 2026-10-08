package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.FileAttenteDAO;
import ma.youcode.model.FileAttente;

public class FileAttenteDAOImpl implements FileAttenteDAO {

    @Override
    public FileAttente save(FileAttente fileAttente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(fileAttente);
            tx.commit();
            return fileAttente;
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
    public Optional<FileAttente> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            FileAttente fileAttente = em.find(FileAttente.class, id);
            return Optional.ofNullable(fileAttente);
        } finally {
            em.close();
        }
    }

    @Override
    public List<FileAttente> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM FileAttente f", FileAttente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<FileAttente> findByPatientId(Long patientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            if (patientId == null) {
                return java.util.Collections.emptyList();
            }
            return em.createQuery(
                    "SELECT f FROM FileAttente f WHERE f.patient.id = :patientId ORDER BY f.heureArrivee ASC",
                    FileAttente.class)
                    .setParameter("patientId", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public FileAttente update(FileAttente fileAttente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileAttente updated = em.merge(fileAttente);
            tx.commit();
            return updated;
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
    public boolean delete(FileAttente fileAttente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileAttente managed = em.contains(fileAttente) ? fileAttente : em.merge(fileAttente);
            em.remove(managed);
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
}
