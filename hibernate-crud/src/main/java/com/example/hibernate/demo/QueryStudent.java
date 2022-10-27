package com.example.hibernate.demo;

import com.example.hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Slf4j
public class QueryStudent {
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
            //start a transaction
            session.beginTransaction();

            // query students
            List<Student> studentList = session.createQuery("from Student", Student.class).getResultList();
            log.info("\n\nAll Students list:");

            // display student
            displayStudents(studentList);

            studentList = session.createQuery("from Student s where s.firstName = 'Amit'", Student.class).getResultList();
            log.info("\n\nStudent whose fistName = Amit");
            displayStudents(studentList);

            studentList = session.createQuery(
                    "from Student s where s.firstName = 'Amit' OR s.lastName = 'Styles'"
                    , Student.class).getResultList();
            log.info("\n\nStudent whose fistName = Amit or lastName = Styles");
            displayStudents(studentList);

            // commit transaction
            session.getTransaction().commit();


            log.info("\nDone!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }

    private static void displayStudents(List<Student> studentList) {
        studentList.stream().forEach(s -> log.info(s+""));
    }
}
