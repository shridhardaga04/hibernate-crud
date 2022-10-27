package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class DeleteStudent {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session;

        try(factory){
            int studId = 7;

            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve students by id
            log.info("Getting student with id : " + studId + "\n");
            Student myStudent = session.get(Student.class, studId);

            // delete student
            log.info("Deleting Student..." + myStudent);
            session.delete(myStudent);
//            session.createQuery("delete from Student where id = 6", Student.class).executeUpdate();

            // commit the transaction
            session.getTransaction().commit();


            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
