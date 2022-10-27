package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class ReadStudentTry {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory){
            // get new session and start transaction
            session.beginTransaction();

            //retrieve students by id
            log.info("\nGetting student with id : 7");
            Student myStudent = session.get(Student.class, 6);
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
