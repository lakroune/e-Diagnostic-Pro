package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.MedecinSpecialisteDAO;
import ma.youcode.model.MedecinSpecialiste;

public class MedecinSpecialisteDAOImpl implements MedecinSpecialisteDAO {

    @Override
    public MedecinSpecialiste save(MedecinSpecialiste medecin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(medecin);
            tx.commit();
            return medecin;
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
    public Optional<MedecinSpecialiste> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            MedecinSpecialiste medecin = em.find(MedecinSpecialiste.class, id);
            if (medecin == null) {
                return Optional.empty();
            }
            medecin.getCreneaux().size();
            return Optional.of(medecin);
        } finally {
            em.close();
        }
    }

    @Override
    public MedecinSpecialiste update(MedecinSpecialiste medecin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            MedecinSpecialiste updatedMedecin = em.merge(medecin);
            tx.commit();
            return updatedMedecin;
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
    public boolean delete(MedecinSpecialiste medecin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.remove(medecin);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            e.printStackTrace();
            return false;

        } finally {
            em.close();
        }
    }

    @Override
    public List<MedecinSpecialiste> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<MedecinSpecialiste> medecins = em.createQuery(
                    "SELECT m FROM MedecinSpecialiste m", MedecinSpecialiste.class)
                    .getResultList();
            for (MedecinSpecialiste medecin : medecins) {
                medecin.getCreneaux().size();
            }
            return medecins;
        } finally {
            em.close();
        }
    }
}