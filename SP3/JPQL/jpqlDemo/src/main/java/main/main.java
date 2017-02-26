/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Janus
 */
//class til at køre opgaverne
public class main {

    public main() {
        Persistence.generateSchema("pu", null);
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
    }

    EntityManagerFactory emf;
    EntityManager em;

    public static void main(String[] args) {
        main m = new main();
        
        //kører opgaverne
        m.opgaver();
        
        //D.1 laver en student og saver til databasen
        //m.createStudent(new Student("You","Zinho"));
        
        //D.2 adder studypoint til den nye student
        //m.AddStudyPoint(m.findStudentById(3), new Studypoint("Testdag",7,6));
    }
    
    public Student findStudentById(int id){
        return(Student) em.createNamedQuery("Student.findById").setParameter("id", id).getSingleResult();
    }

    public void opgaver() {
        //C.1 Finds all students 
        List<Student> allstudents = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();

        for (Student allstudent : allstudents) {
            System.out.println("Printing all students' firstname: " + allstudent.getFirstname());
        }
        //C.2 Finds all students with the firname jan
        List<Student> studentJan = (List<Student>) em.createNamedQuery("Student.findByFirstname").setParameter("firstname", "jan").getResultList();

        for (Student student : studentJan) {
            System.out.println("Printing all Jans: " + student.getFirstname());
        }

        //C.3 Finds all students with the lastname olsen
        List<Student> studentOlsen = (List<Student>) em.createNamedQuery("Student.findByLastname").setParameter("lastname", "olsen").getResultList();

        for (Student student : studentOlsen) {
            System.out.println("Printing all Olsens: " + student.getFirstname() + " " + student.getLastname());
        }

        //C.4 Finds a student by id and their sum of studypoints
        Student student = (Student) em.createNamedQuery("Student.findById").setParameter("id", 1).getSingleResult();
        int sum = 0;
        for (Studypoint sp : student.getStudypointList()) {
            sum += sp.getScore();
        }
        System.out.println("Printing studypoints for a student: " + student.getFirstname() + ": " + sum);

        //C.5 Finds total sum of studypoint scores given to all students
        List<Student> all = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        sum = 0;
        for (Student st : all) {
            for (Studypoint sp : student.getStudypointList()) {
                sum += sp.getScore();
            }
        }
        System.out.println("Total sum of all students studypoints: " + sum);

        //C.6 Finds the student with the most studypoints
        List<Student> great = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        String Student = "Nobody";

        int max = 0;
        for (Student st : great) {
            sum = 0;
            for (Studypoint sp : st.getStudypointList()) {
                sum += sp.getScore();
            }
            if (sum > max) {
                Student = st.getFirstname() + " " + st.getLastname();
                max = sum;
            }
        }
        System.out.println("The student with the most studypoints is: " + Student);

        //C.7 Finds the student with the least studypoints
        List<Student> low = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        Student = "Nobody";

        int lowest = 100;
        for (Student st : low) {
            sum = 0;
            for (Studypoint sp : st.getStudypointList()) {
                sum += sp.getScore();
            }
            if (sum < lowest) {
                Student = st.getFirstname() + " " + st.getLastname();
                lowest = sum;
            }
        }
        System.out.println("The student with the lowest amount of studypoints is: " + Student);
    }

    public void createStudent(Student st) {
        try {
            em.getTransaction().begin();
            em.persist(st);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void AddStudyPoint(Student st, Studypoint sp){
        st.addStudyPoint(sp);
        em.getTransaction().begin();
            em.persist(sp);
            em.getTransaction().commit();
    }
}
