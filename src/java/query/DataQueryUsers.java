/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

import entity.HoogieUser;
import exceptions.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author I320736
 */
public class DataQueryUsers {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQueryUsers() {
        emf = Persistence.createEntityManagerFactory("HoogiesPU");
        em = emf.createEntityManager();
        emf.getCache().evictAll();
    }

    public void addUser(HoogieUser user) throws EntityExistsException {
        try {
            em.getTransaction().begin();
            if (doesUserNameExist(user.getUserName())) {
                throw new EntityExistsException("User already exists");
            }
            if (doesEmailExist(user.getEmail())) {
                throw new EntityExistsException("Email already exists");
            }

            em.persist(user);
            em.getTransaction().commit();

        } catch (EntityExistsException e) {
            throw e;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public HoogieUser updateUser(HoogieUser user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return user;
    }

    public HoogieUser getUserByName(String username) {
        return em.createNamedQuery("HoogieUser.findByUserName", HoogieUser.class).setParameter("userName", username).getSingleResult();
    }
    
    public HoogieUser getUserByEmail(String email) {
        return em.createNamedQuery("HoogieUser.findByEmail", HoogieUser.class).setParameter("email", email).getSingleResult();
    }
    
    public boolean doesUserNameExist(String username) {
        return em.createNamedQuery("HoogieUser.findByUserName", HoogieUser.class).setParameter("userName", username).getResultList().size() > 0;
    }
    
    public boolean doesEmailExist(String email) {
        return em.createNamedQuery("HoogieUser.findByEmail", HoogieUser.class).setParameter("email", email).getResultList().size() > 0;
    }
    
    public boolean validateUser(String username, String password) {
        try {
            HoogieUser u = em.createNamedQuery("HoogieUser.control", HoogieUser.class).setParameter("userName", username).setParameter("password", password).getSingleResult();
            return u != null;
        } catch (NoResultException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }       
        return false;
    }
}
