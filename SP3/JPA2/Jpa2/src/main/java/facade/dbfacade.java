package facade;

import entity.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Janus
 */
public class dbfacade {

    EntityManagerFactory emf;
    EntityManager em;

    public dbfacade() {
        Persistence.generateSchema("pu", null);
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
    }

    public void saveTransaction(Object o) {
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addPerson(Person p) {
        saveTransaction(p);
    }
}
