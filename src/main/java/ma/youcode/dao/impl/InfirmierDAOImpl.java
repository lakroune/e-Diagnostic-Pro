package ma.youcode.dao.impl;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.youcode.config.JPAUtil;
import ma.youcode.dao.InfirmierDAO;
import ma.youcode.model.Infirmier;

public class InfirmierDAOImpl implements InfirmierDAO {

    @Override
    public Infirmier save(Infirmier infirmier) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(infirmier);
            tx.commit();
            return infirmier;
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
    public Optional<Infirmier> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Infirmier infirmier = em.find(Infirmier.class, id);
            return Optional.ofNullable(infirmier);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public Infirmier update(Infirmier infirmier) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Infirmier updatedInfirmier = em.merge(infirmier);
            tx.commit();
            return updatedInfirmier;
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
    public void delete(Infirmier infirmier) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Infirmier managedInfirmier = em.contains(infirmier) ? infirmier : em.merge(infirmier);
            em.remove(managedInfirmier);
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
    public List<Infirmier> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT i FROM Infirmier i", Infirmier.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}