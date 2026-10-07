package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.PatientDAO;
import ma.youcode.model.Patient;
import ma.youcode.model.enums.StatutFile;

public class PatientDAOImpl implements PatientDAO {

    public PatientDAOImpl() {
    }

    @Override
    public Patient save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(patient);
            tx.commit();
            return patient;
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
    public Optional<Patient> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Patient patient = em.find(Patient.class, id);
            if (patient != null) {
                patient.getSignesVitaux().size();
                patient.getFileAttentes().size();
            }
            return Optional.ofNullable(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Patient> findByNumSecuriteSociale(String numSecuriteSociale) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Patient p WHERE p.numSecuriteSociale = :numero",
                    Patient.class)
                    .setParameter("numero", numSecuriteSociale)
                    .getResultStream()
                    .findFirst();
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public Patient update(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Patient pmPatient = em.merge(patient);
            tx.commit();
            return pmPatient;
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
    public boolean delete(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Patient managedPatient = em.contains(patient) ? patient : em.merge(patient);
            em.remove(managedPatient);
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
    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p", Patient.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Patient> getListAttente() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT p FROM Patient p " +
                            "JOIN p.fileAttentes f " +
                            "WHERE f.statut = :statut " +
                            "ORDER BY f.heureArrivee ASC",
                    Patient.class)
                    .setParameter("statut", StatutFile.EN_ATTENTE)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}