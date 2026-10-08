package ma.youcode.service;

import java.util.List;
import java.util.Optional;

import ma.youcode.dao.DemandeTeleExpertiseDAO;
import ma.youcode.dao.impl.DemandeTeleExpertiseDAOImpl;
import ma.youcode.model.DemandeTeleExpertise;

public class DemandeTeleExpertiseService {

    private final DemandeTeleExpertiseDAO demandeTeleExpertiseDAO;

    public DemandeTeleExpertiseService(DemandeTeleExpertiseDAO demandeTeleExpertiseDAO) {
        this.demandeTeleExpertiseDAO = demandeTeleExpertiseDAO;
    }

    public DemandeTeleExpertiseService() {
        this.demandeTeleExpertiseDAO = new DemandeTeleExpertiseDAOImpl();
    }

    public DemandeTeleExpertise create(DemandeTeleExpertise d) {
        return demandeTeleExpertiseDAO.save(d);
    }

    public DemandeTeleExpertise update(DemandeTeleExpertise d) {
        // Long id, LocalDateTime dateDemande, String question,
        // String donneesAnalyses, PrioriteExpertise priorite,
        // StatutExpertise statut, String avisExpert, String recommandations

        DemandeTeleExpertise existingDemande = demandeTeleExpertiseDAO.findById(d.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Demande de télé-expertise non trouvée avec l'ID : " + d.getId()));

        existingDemande.setDateDemande(d.getDateDemande());
        existingDemande.setQuestion(d.getQuestion());
        existingDemande.setDonneesAnalyses(d.getDonneesAnalyses());
        existingDemande.setPriorite(d.getPriorite());
        existingDemande.setStatut(d.getStatut());
        existingDemande.setAvisExpert(d.getAvisExpert());
        existingDemande.setRecommandations(d.getRecommandations());

        return demandeTeleExpertiseDAO.update(d);
    }

    public Optional<DemandeTeleExpertise> findById(long id) {
        return demandeTeleExpertiseDAO.findById(id);
    }

    public List<DemandeTeleExpertise> findAll() {
        return demandeTeleExpertiseDAO.findAll();
    }

    public boolean delete(Long id) {
        DemandeTeleExpertise demandeTeleExpertise = demandeTeleExpertiseDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Demande de télé-expertise non trouvée avec l'ID : " + id));
        return demandeTeleExpertiseDAO.delete(demandeTeleExpertise);
    }
}
