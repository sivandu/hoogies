/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

import entity.Review;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author I320736
 */
public class DataQueryReviews {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQueryReviews() {
        emf = Persistence.createEntityManagerFactory("HoogiesPU");
        em = emf.createEntityManager();
    }

    public void addReview(Review review) {
        try {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateReview(Review review) {
        try {
            em.getTransaction().begin();
            em.merge(review);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteReview(Review review) {
        try {
            em.getTransaction().begin();
            review.detach();
            em.remove(em.merge(review));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
        
}
