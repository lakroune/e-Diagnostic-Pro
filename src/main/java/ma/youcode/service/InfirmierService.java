package ma.youcode.service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.InfirmierDAO;
import ma.youcode.dao.impl.InfirmierDAOImpl;
import ma.youcode.model.Infirmier;

public class InfirmierService {

    private final InfirmierDAO infirmierDAO;

    public InfirmierService(InfirmierDAO infirmierDAO) {
        this.infirmierDAO = infirmierDAO;
    }

    public InfirmierService() {
        this.infirmierDAO = new InfirmierDAOImpl();
    }

    public Infirmier save(Infirmier i) {
        return infirmierDAO.save(i);
    }

    public Infirmier update(long id, Infirmier i) {
        Infirmier existingInfirmier = infirmierDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Infirmier non trouvé avec l'ID : " + id));

        existingInfirmier.setNom(i.getNom());
        existingInfirmier.setPrenom(i.getPrenom());
        existingInfirmier.setEmail(i.getEmail());
        existingInfirmier.setMotDePasse(i.getMotDePasse());
        existingInfirmier.setTelephone(i.getTelephone());
        existingInfirmier.setActif(i.isActif());
        existingInfirmier.setMatriculePro(i.getMatriculePro());
        return infirmierDAO.update(existingInfirmier);
    }

    public Optional<Infirmier> findById(long id) {
        return infirmierDAO.findById(id);
    }

    public boolean delete(Long id) {
        Infirmier infirmier = infirmierDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Infirmier non trouvé avec l'ID : " + id));
        return infirmierDAO.delete(infirmier);
    }

}
