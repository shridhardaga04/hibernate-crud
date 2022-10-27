package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class CreateStudent {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // use the session obj to save Java obj
            // create  a Student obj

            log.info("Creating new Student obj...");
            Student tempStud = new Student("Shridhar", "Daga", "shri@zemosolabs.com");

            //start a transaction
            session.beginTransaction();

            //save the Student obj {alternate to session.save(obj)}
            log.info("Saving the student object...");
            session.persist(tempStud);

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
        finally {
            factory.close();
        }
    }
}
