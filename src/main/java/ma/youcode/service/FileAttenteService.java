package ma.youcode.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.FileAttenteDAO;
import ma.youcode.dao.impl.FileAttenteDAOImpl;
import ma.youcode.model.FileAttente;
import ma.youcode.model.Infirmier;
import ma.youcode.model.Patient;
import ma.youcode.model.enums.StatutFile;

public class FileAttenteService {

    private final FileAttenteDAO fileAttenteDAO;

    public FileAttenteService() {
        this.fileAttenteDAO = new FileAttenteDAOImpl();
    }

    public FileAttenteService(FileAttenteDAO fileAttenteDAO) {
        this.fileAttenteDAO = fileAttenteDAO;
    }

    public FileAttente ajouter(Patient patient) {
        return ajouter(patient, null);
    }

    public FileAttente ajouter(Patient patient, Infirmier infirmier) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être null.");
        }

        FileAttente fileAttente = new FileAttente();
        fileAttente.setPatient(patient);
        fileAttente.setInfirmier(infirmier);
        fileAttente.setHeureArrivee(LocalDateTime.now());
        fileAttente.setStatut(StatutFile.EN_ATTENTE);

        return save(fileAttente);
    }

    public FileAttente save(FileAttente fileAttente) {
        return fileAttenteDAO.save(fileAttente);
    }

    public Optional<FileAttente> findById(Long id) {
        return fileAttenteDAO.findById(id);
    }

    public List<FileAttente> findAll() {
        return fileAttenteDAO.findAll();
    }

    public FileAttente update(Long id, FileAttente fileAttenteDetails) {
        FileAttente existing = fileAttenteDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File d'attente non trouvée avec l'ID : " + id));

        existing.setHeureArrivee(fileAttenteDetails.getHeureArrivee());
        existing.setStatut(fileAttenteDetails.getStatut());
        existing.setInfirmier(fileAttenteDetails.getInfirmier());
        existing.setPatient(fileAttenteDetails.getPatient());

        return fileAttenteDAO.update(existing);
    }

    public boolean delete(Long id) {
        FileAttente fileAttente = fileAttenteDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File d'attente non trouvée avec l'ID : " + id));
        return fileAttenteDAO.delete(fileAttente);
    }
}
