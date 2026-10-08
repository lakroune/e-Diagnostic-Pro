package ma.youcode.service;

import java.util.List;
import java.util.Optional;

import ma.youcode.dao.MedecinSpecialisteDAO;
import ma.youcode.dao.impl.MedecinSpecialisteDAOImpl;
import ma.youcode.model.MedecinSpecialiste;

public class MedecinSpecialisteService {

    private MedecinSpecialisteDAO medecinSpecialisteDAO;

    public MedecinSpecialisteService(MedecinSpecialisteDAO medecinSpecialisteDAO) {
        this.medecinSpecialisteDAO = medecinSpecialisteDAO;
    }

    public MedecinSpecialisteService() {
        this.medecinSpecialisteDAO = new MedecinSpecialisteDAOImpl();
    }

    public MedecinSpecialiste create(MedecinSpecialiste m) {
        return medecinSpecialisteDAO.save(m);
    }

    public MedecinSpecialiste update(MedecinSpecialiste m) {
        // Long id, String nom, String prenom, String email, String motDePasse,
        // String telephone, boolean actif, SpecialiteMedicale specialite,
        // Double tarifExpertise, Integer dureeConsultationMin

        MedecinSpecialiste existingMedecin = medecinSpecialisteDAO.findById(m.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Médecin spécialiste non trouvé avec l'ID : " + m.getId()));
        existingMedecin.setNom(m.getNom());
        existingMedecin.setPrenom(m.getPrenom());
        existingMedecin.setEmail(m.getEmail());
        existingMedecin.setMotDePasse(m.getMotDePasse());
        existingMedecin.setActif(m.isActif());
        existingMedecin.setSpecialite(m.getSpecialite());
        existingMedecin.setTarifExpertise(m.getTarifExpertise());
        existingMedecin.setDureeConsultationMin(m.getDureeConsultationMin());
        return medecinSpecialisteDAO.update(existingMedecin);
    }

    public boolean delete(Long id) {
        MedecinSpecialiste medecinSpecialiste = medecinSpecialisteDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médecin spécialiste non trouvé avec l'ID : " + id));
        return medecinSpecialisteDAO.delete(medecinSpecialiste);
    }

    public Optional<MedecinSpecialiste> findById(long id) {
        return medecinSpecialisteDAO.findById(id);
    }

    public List<MedecinSpecialiste> findAll() {
        return medecinSpecialisteDAO.findAll();
    }
}
