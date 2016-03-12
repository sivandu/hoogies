/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

import entity.Interest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author I320736
 */
public class DataQueryInterests {

    EntityManagerFactory emf;
    EntityManager em;

    public DataQueryInterests() {
        emf = Persistence.createEntityManagerFactory("HoogiesPU");
        em = emf.createEntityManager();
    }

    public List<Interest> getAllInterests() {
        try {
            return em.createNamedQuery("Interest.findAll").getResultList();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Interest getInterestById(int id) {
        try {
            return em.createNamedQuery("Interest.findByInterestId", Interest.class).setParameter("interestId", id).getSingleResult();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        
        return new Interest();
    }
}
