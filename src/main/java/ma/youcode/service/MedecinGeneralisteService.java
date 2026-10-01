package ma.youcode.service;

import java.util.Optional;

import ma.youcode.dao.MedecinGeneralisteDAO;
import ma.youcode.dao.impl.MedecinGeneralisteDAOImpl;
import ma.youcode.model.MedecinGeneraliste;

public class MedecinGeneralisteService {

    // Long id, String nom, String prenom, String email, String motDePasse,
    // String telephone, boolean actif, String matriculeOrdre

    private MedecinGeneralisteDAO medecinGeneralisteDAO;

    public MedecinGeneralisteService(MedecinGeneralisteDAO medecinGeneralisteDAO) {
        this.medecinGeneralisteDAO = medecinGeneralisteDAO;
    }

    public MedecinGeneralisteService() {
        this.medecinGeneralisteDAO = new MedecinGeneralisteDAOImpl();
    }

    public MedecinGeneraliste create(MedecinGeneraliste m) {
        return medecinGeneralisteDAO.save(m);
    }

    public MedecinGeneraliste update(MedecinGeneraliste m) {

        MedecinGeneraliste existingMedecin = medecinGeneralisteDAO.findById(m.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Médecin généraliste non trouvé avec l'ID : " + m.getId()));
        existingMedecin.setNom(m.getNom());
        existingMedecin.setPrenom(m.getPrenom());
        existingMedecin.setEmail(m.getEmail());
        existingMedecin.setMotDePasse(m.getMotDePasse());
        existingMedecin.setTelephone(m.getTelephone());
        existingMedecin.setActif(m.isActif());
        existingMedecin.setMatriculeOrdre(m.getMatriculeOrdre());

        return medecinGeneralisteDAO.update(existingMedecin);
    }

    public boolean delete(Long id) {
        MedecinGeneraliste medecinGeneraliste = medecinGeneralisteDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médecin généraliste non trouvé avec l'ID : " + id));
        return medecinGeneralisteDAO.delete(medecinGeneraliste);
    }

    public Optional<MedecinGeneraliste> findById(long id) {
        return medecinGeneralisteDAO.findById(id);
    }
}
