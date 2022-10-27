package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class PrimaryKeyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory; session){
            // use the session obj to save Java obj
            // create  a Student obj

            log.info("Creating 3 new Student obj...");
            Student tempStud1 = new Student("John", "Doe", "john@zemosolabs.com");
            Student tempStud2 = new Student("Sumit", "Raj", "sumit@zemosolabs.com");
            Student tempStud3 = new Student("Amit", "Kumar", "amit@zemosolabs.com");


            //start a transaction
            session.beginTransaction();

            //save the Student obj {alternate to session.save(obj)}
            log.info("Saving the student object...");
            session.persist(tempStud1);
            session.persist(tempStud2);
            session.persist(tempStud3);


            // commit transaction
            session.getTransaction().commit();
            log.info("It's Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}

