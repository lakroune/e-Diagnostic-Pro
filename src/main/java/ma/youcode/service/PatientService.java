package ma.youcode.service;

import java.util.List;

import ma.youcode.dao.PatientDAO;
import ma.youcode.dao.impl.PatientDAOImpl;
import ma.youcode.model.Patient;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAOImpl();

    public void ajouterPatient(String nom, String prenom, String telephone) {

    }

    public List<Patient> listerPatients() {
        return patientDAO.findAll();
    }
}