package ma.youcode.dao;

import java.util.List;
import java.util.Optional;

import ma.youcode.model.DemandeTeleExpertise;

public interface DemandeTeleExpertiseDAO {

    DemandeTeleExpertise save(DemandeTeleExpertise a);

    Optional<DemandeTeleExpertise> findById(Long id);

    DemandeTeleExpertise update(DemandeTeleExpertise demandeTeleExpertise);

    boolean delete(DemandeTeleExpertise demandeTeleExpertise);

    List<DemandeTeleExpertise> findAll();
}
