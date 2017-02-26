/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBfacade;

import entity.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    
    public static void main(String[] args) {
        //run methods here
    }
    
    //method for saving to the database
    public void saveTransaction(Object o){
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    //method for creating a user in the database
    public void createUser(ProjectUser user){
        saveTransaction(user);
    }
    
    //method finding a user in the database
    public void findUser(int id){
        try {
            em.getTransaction().begin();
            em.find(ProjectUser.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //method for getting a list of all users
    public List<ProjectUser> getAllUsers(){
        Query queryFindAllUsers = em.createQuery("SELECT s FROM ProjectUser s");
        return queryFindAllUsers.getResultList();
    }
    
    //method for creating a project in the database
    public void createUser(Project project){
        saveTransaction(project);
    }
    
    //method for assinging user to a project
    public void assingUser(ProjectUser user){
        
    }
    
    //method for finding a project
    public void findProject(int id){
        try {
            em.getTransaction().begin();
            em.find(ProjectUser.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
