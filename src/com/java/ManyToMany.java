package com.java;

import com.model.Course;
import com.model.Student;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class ManyToMany {
    @Test
    public void demo1(){

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Student student1 = new Student();
        student1.setName("谷志雄");
        Student student2 = new Student();
        student2.setName("梁红");

        Course course1 = new Course();
        course1.setName("高数");
        Course course2 = new Course();
        course2.setName("英语");

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        course1.getStudents().add(student1);
        course2.getStudents().add(student1);

        student2.getCourses().add(course2);
        course2.getStudents().add(student2);

        session.save(student1);
        session.save(student2);
        session.save(course1);
        session.save(course2);

        transaction.commit();
        session.close();


    }

    @Test
    public void demo2(){

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Student student1 = new Student();
        student1.setName("谷志雄");
        Student student2 = new Student();
        student2.setName("梁红");

        Course course1 = new Course();
        course1.setName("高数");
        Course course2 = new Course();
        course2.setName("英语");

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        course1.getStudents().add(student1);
        course2.getStudents().add(student1);

        student2.getCourses().add(course2);
        course2.getStudents().add(student2);

        session.save(student1);
        session.save(student2);

        transaction.commit();
        session.close();


    }
}
