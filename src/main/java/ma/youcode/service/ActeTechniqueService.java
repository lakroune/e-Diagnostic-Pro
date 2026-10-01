package ma.youcode.service;

import ma.youcode.dao.ActeTechniqueDAO;
import ma.youcode.dao.impl.ActeTechniqueDAOImpl;

public class ActeTechniqueService {
    private final ActeTechniqueDAO acteTechniqueDAO;

    public ActeTechniqueService() {
        this.acteTechniqueDAO = new ActeTechniqueDAOImpl();
    }

    public ActeTechniqueService(ActeTechniqueDAO acteTechniqueDAO1) {
        this.acteTechniqueDAO = acteTechniqueDAO1;
    }
}
