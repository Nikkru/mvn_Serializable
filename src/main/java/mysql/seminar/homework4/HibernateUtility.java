package mysql.seminar.homework4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HibernateUtility {
    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration()
                    .configure("hibernate3.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() {
        return  sessionFactory.openSession();
    }

    public static void addCourse(String name, int duration) {
        Session session = getSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Course course = new Course(name, duration);
        session.save(course);
        transaction.commit();
        session.close();
        System.out.println("Course " + course + " was successfully add");
    }

    public static List getCourses() {
        Session session = getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        List <Course> courses = session.createQuery("FROM Course").list();

        transaction.commit();
        session.close();
        for (Course course_ : courses) {
            System.out.println(course_);
        }
        return courses;
    }

    public static void updateCourse(int id, String title, int duration) {
        Session session = getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Course course = session.get(Course.class, id);
        course.setName(title);
        course.setDuration(duration);
        session.update(course);
        System.out.println("Course number " + course.getId() + " was successfully update\n" + course);

        transaction.commit();
        session.close();
    }

    public static void removeCourse(int id) {
        Session session = getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Course course = session.get(Course.class, id);
        String title = course.getName();
        session.delete(course);

        transaction.commit();
        session.close();
        System.out.println("Course " + title + " was successfully remove");
    }
}
