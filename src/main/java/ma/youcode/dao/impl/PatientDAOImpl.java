package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.youcode.dao.PatientDAO;
import ma.youcode.model.Patient;

public class PatientDAOImpl implements PatientDAO {

    @PersistenceContext
    private EntityManager em;

    public PatientDAOImpl() {
    }

    public PatientDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Patient save(Patient patient) {
        em.persist(patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(em.find(Patient.class, id));
    }

    @Override
    public Patient update(Patient patient) {
        return em.merge(patient);
    }

    @Override
    public void delete(Patient patient) {
        em.remove(patient);
    }

    @Override
    public List<Patient> findAll() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }
}