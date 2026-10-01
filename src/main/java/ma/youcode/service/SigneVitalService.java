package ma.youcode.service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.SigneVitalDAO;
import ma.youcode.dao.impl.SigneVitalDAOImpl;
import ma.youcode.model.SigneVital;

public class SigneVitalService {

    private final SigneVitalDAO signeVitalDAO;

    public SigneVitalService(SigneVitalDAO signeVitalDAO) {
        this.signeVitalDAO = signeVitalDAO;
    }

    public SigneVitalService() {
        this.signeVitalDAO = new SigneVitalDAOImpl();
    }

    public SigneVital create(SigneVital s) {
        return signeVitalDAO.save(s);
    }

    public SigneVital update(Long id, SigneVital s) {
        SigneVital exist = signeVitalDAO.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Signe vital non trouvé"));

        // Long id, String tensionArterielle, Integer frequenceCardiaque,
        // Double temperatureCorporelle, Integer frequenceRespiratoire,
        // Double poidsKg, Double tailleCm, LocalDateTime datePrise)

        exist.setTensionArterielle(s.getTensionArterielle());
        exist.setFrequenceCardiaque(s.getFrequenceCardiaque());
        exist.setTemperatureCorporelle(s.getTemperatureCorporelle());
        exist.setFrequenceRespiratoire(s.getFrequenceRespiratoire());
        exist.setPoidsKg(s.getPoidsKg());
        exist.setTailleCm(s.getTailleCm());
        exist.setDatePrise(s.getDatePrise());

        return signeVitalDAO.update(exist);
    }

    public Optional<SigneVital> findById(long id) {
        return signeVitalDAO.findById(id);
    }

    public boolean delete(Long id) {
        SigneVital signeVital = signeVitalDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Signe vital non trouvé avec l'ID : " + id));
        return signeVitalDAO.delete(signeVital);
    }
}
