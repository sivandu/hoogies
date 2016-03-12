/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

import entity.HoogieGroup;
import exceptions.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author I320736
 */
public class DataQueryGroups {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQueryGroups() {
        emf = Persistence.createEntityManagerFactory("HoogiesPU");
        em = emf.createEntityManager();
    }

    public void addGroup(HoogieGroup group) throws EntityExistsException {
        try {
            em.getTransaction().begin();
            if (em.createNamedQuery("HoogieGroup.findByGroupName", HoogieGroup.class).setParameter("groupName", group.getGroupName()).getResultList().size() == 1) {
                throw new EntityExistsException("Group already exists");
            }
            em.merge(group);
            em.getTransaction().commit();

        } catch (EntityExistsException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateGroup(HoogieGroup group) {
        try {
            em.getTransaction().begin();
            em.merge(group);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteGroup(HoogieGroup group) {
        try {
            em.getTransaction().begin();
            group.detach();
            em.remove(em.merge(group));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<HoogieGroup> getGroupsByKey(String key) {
        try {
            return em.createNamedQuery("HoogieGroup.findByKeyOrInterest", HoogieGroup.class).setParameter("key", '%' + key + '%').getResultList();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<HoogieGroup> getGroupsByInterest(String interest) {
        try {
            return em.createNamedQuery("HoogieGroup.findByInterest", HoogieGroup.class).setParameter("interest", interest).getResultList();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public HoogieGroup getGroupById(int id) {
        try {
            return em.createNamedQuery("HoogieGroup.findByGroupId", HoogieGroup.class).setParameter("groupId", id).getSingleResult();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        return new HoogieGroup();
    }
}
