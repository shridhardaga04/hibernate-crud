package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class ReadStudent {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory){
            // use the session obj to save Java obj
            // create  a Student obj

            log.info("Creating new Student obj...");
            Student tempStud = new Student("Zyan", "Malik", "harry@zemosolabs.com");

            //start a transaction
            session.beginTransaction();

            //save the Student obj {alternate to session.save(obj)}
            log.info("Saving the student object...");
            log.info(tempStud + "");
            session.persist(tempStud);

            // commit transaction
            session.getTransaction().commit();

            // get saved students PK (id)
            log.info("Saved student. Generated id : " + tempStud.getId());

            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve students by id
            log.info("\nGetting student with id : " + tempStud.getId());
            Student myStudent = session.get(Student.class, tempStud.getId());
            log.info("Get complete : " + myStudent);

            // commit the transaction
            session.getTransaction().commit();

            log.info("Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
