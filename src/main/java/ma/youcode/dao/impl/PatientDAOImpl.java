package ma.youcode.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.PatientDAO;
import ma.youcode.model.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public void save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(patient);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
        } finally {
            em.close();
        }
    }
}