package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.DemandeTeleExpertiseDAO;
import ma.youcode.model.DemandeTeleExpertise;

public class DemandeTeleExpertiseDAOImpl implements DemandeTeleExpertiseDAO {

    @Override
    public DemandeTeleExpertise save(DemandeTeleExpertise demande) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(demande);
            tx.commit();
            return demande;
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<DemandeTeleExpertise> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            DemandeTeleExpertise demande = em.createQuery(
                    "SELECT d FROM DemandeTeleExpertise d " +
                            "LEFT JOIN FETCH d.medecinGeneraliste " +
                            "LEFT JOIN FETCH d.medecinSpecialiste " +
                            "LEFT JOIN FETCH d.consultation " +
                            "LEFT JOIN FETCH d.creneau " +
                            "WHERE d.id = :id",
                    DemandeTeleExpertise.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(demande);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public DemandeTeleExpertise update(DemandeTeleExpertise demande) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DemandeTeleExpertise updatedDemande = em.merge(demande);
            tx.commit();
            return updatedDemande;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(DemandeTeleExpertise demande) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(demande);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<DemandeTeleExpertise> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT d FROM DemandeTeleExpertise d " +
                            "LEFT JOIN FETCH d.medecinGeneraliste " +
                            "LEFT JOIN FETCH d.medecinSpecialiste",
                    DemandeTeleExpertise.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}