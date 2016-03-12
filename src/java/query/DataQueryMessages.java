/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

import entity.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author I320736
 */
public class DataQueryMessages {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQueryMessages() {
        emf = Persistence.createEntityManagerFactory("HoogiesPU");
        em = emf.createEntityManager();
    }

    public void addMessage(Message message) {
        try {
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateMessage(Message message) {
        try {
            em.getTransaction().begin();
            em.merge(message);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteMessage(Message message) {
        try {
            em.getTransaction().begin();
            message.detach();
            em.remove(em.merge(message));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
