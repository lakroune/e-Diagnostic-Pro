package ma.youcode.service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.CreneauDAO;
import ma.youcode.dao.impl.CreneauDAOImpl;
import ma.youcode.model.Creneau;

public class CreneauService {

    private final CreneauDAO creneauDAO;

    public CreneauService(CreneauDAO creneauDAO) {
        this.creneauDAO = creneauDAO;
    }

    public CreneauService() {
        this.creneauDAO = new CreneauDAOImpl();
    }

    public Creneau create(Creneau c) {
        return creneauDAO.save(c);
    }

    public Creneau update(Long id, Creneau c) {
        Creneau existingCreneau = creneauDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creneau non trouvé avec l'ID : " + id));
        // Long id, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin,
        // StatutCreneau statut)
        existingCreneau.setDateHeureDebut(c.getDateHeureDebut());
        existingCreneau.setDateHeureFin(c.getDateHeureFin());
        existingCreneau.setStatut(c.getStatut());

        return creneauDAO.update(existingCreneau);
    }

    public Optional<Creneau> findById(long id) {
        return creneauDAO.findById(id);
    }

    public boolean delete(Long id) {
        Creneau creneau = creneauDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Creneau non trouvé avec l'ID : " + id));
        return creneauDAO.delete(creneau);
    }
}
