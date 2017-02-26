/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Janus
 */
public class Main {
    EntityManagerFactory emf;
    EntityManager em;
    public Main() {
        Persistence.generateSchema("pu", null);
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        
        
    }
    
}
