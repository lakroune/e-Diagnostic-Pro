package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.ActeTechniqueDAO;
import ma.youcode.model.ActeTechnique;


public class ActeTechniqueDAOImpl implements ActeTechniqueDAO {

    @Override
    public ActeTechnique save(ActeTechnique a) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
            return a;
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
    public Optional<ActeTechnique> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ActeTechnique acte = em.find(ActeTechnique.class, id);
            return Optional.ofNullable(acte);
        } finally {
            em.close();
        }
    }

    @Override
    public ActeTechnique update(ActeTechnique acteTechnique) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ActeTechnique updatedActe = em.merge(acteTechnique);
            tx.commit();
            return updatedActe;
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
    public void delete(ActeTechnique acteTechnique) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(acteTechnique);
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
    public List<ActeTechnique> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM ActeTechnique a", ActeTechnique.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}