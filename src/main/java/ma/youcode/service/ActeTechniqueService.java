package ma.youcode.service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.dao.ActeTechniqueDAO;
import ma.youcode.dao.impl.ActeTechniqueDAOImpl;
import ma.youcode.model.ActeTechnique;

public class ActeTechniqueService {
    private final ActeTechniqueDAO acteTechniqueDAO;

    public ActeTechniqueService() {
        this.acteTechniqueDAO = new ActeTechniqueDAOImpl();
    }

    public ActeTechniqueService(ActeTechniqueDAO acteTechniqueDAO1) {
        this.acteTechniqueDAO = acteTechniqueDAO1;
    }

    public ActeTechnique create(ActeTechnique a) {
        return acteTechniqueDAO.save(a);
    }

    public ActeTechnique update(Long id, ActeTechnique a) {
        ActeTechnique exist = acteTechniqueDAO.findById(id).orElseThrow(
                () -> new EntityNotFoundException("acte technique non trouve"));

        exist.setLibelle(a.getLibelle());
        exist.setCode(a.getCode());
        exist.setTarif(a.getTarif());
        exist.setDescription(a.getDescription());

        return acteTechniqueDAO.update(exist);
    }

    public Optional<ActeTechnique> findById(long id) {
        return acteTechniqueDAO.findById(id);
    }

    public boolean delete(Long id) {
        ActeTechnique acteTechnique = acteTechniqueDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("acte technique non trouvé avec l'ID : " + id));
        return acteTechniqueDAO.delete(acteTechnique);
    }
}
