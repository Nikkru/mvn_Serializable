package mysql.seminar.homework4;

public class Program {
    public static void main(String[] args) {
//        HibernateUtility.addCourse("Java Kit + Hibernate", 40);
        HibernateUtility.getCourses();
//        HibernateUtility.updateCourse(15, "Java Just Kit +", 13);
        HibernateUtility.removeCourse(10);
        HibernateUtility.getCourses();
    }
}
