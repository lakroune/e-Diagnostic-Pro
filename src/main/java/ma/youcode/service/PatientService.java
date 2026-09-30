package ma.youcode.service;

import ma.youcode.dao.PatientDAO;
import ma.youcode.dao.impl.PatientDAOImpl;
import ma.youcode.model.Patient;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAOImpl();

    public void ajouterPatient(String nom, String prenom, String telephone) {
        Patient patient = new Patient(nom, prenom, telephone);
        patientDAO.save(patient);
    }

    public List<Patient> listerPatients() {
        return patientDAO.findAll();
    }
}