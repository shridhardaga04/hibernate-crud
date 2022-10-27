package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class UpdateStudent {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory){
            int studId = 5;

            // get new session and start transaction
            session.beginTransaction();

            //retrieve students by id
            log.info("Getting student with id : " + studId + "\n");
            Student myStudent = session.get(Student.class, studId);

            // update name
            log.info("Updating Students...\n");
            myStudent.setLastName("Styles");

            // commit the transaction
            session.getTransaction().commit();

            // new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // bulk update student email
            log.info("Update email for all students...\n");
            session.createQuery("update Student set email='random@yahoo.com'").executeUpdate();
            log.info("query executed!\n");

            // commit
            session.getTransaction().commit();

            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
