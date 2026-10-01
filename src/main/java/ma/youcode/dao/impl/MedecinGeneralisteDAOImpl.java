package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.MedecinGeneralisteDAO;
import ma.youcode.model.MedecinGeneraliste;

public class MedecinGeneralisteDAOImpl implements MedecinGeneralisteDAO {

    @Override
    public MedecinGeneraliste save(MedecinGeneraliste medecin) {
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
    public Optional<MedecinGeneraliste> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            MedecinGeneraliste mGeneraliste = em.find(MedecinGeneraliste.class, id);
            return Optional.ofNullable(mGeneraliste);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public MedecinGeneraliste update(MedecinGeneraliste medecin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            tx.begin();

            MedecinGeneraliste mgUpdate = em.merge(medecin);
            tx.commit();

            return mgUpdate;
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(MedecinGeneraliste medecin) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(medecin);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<MedecinGeneraliste> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM MedecinGeneraliste m", MedecinGeneraliste.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}