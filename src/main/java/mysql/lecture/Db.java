package mysql.lecture;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "M131231l";

    public static void con() {
//        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            Statement statement = con.createStatement();
////            statement.execute("DROP SCHEMA `test`");
////            statement.execute("CREATE SCHEMA `test`");
////            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY (`id`));");
////            statement.execute("INSERT INTO `test`.`table` (`id`, `firstname`, `lastname`)\n" + "VALUES (1, 'Иванов', 'Ivan')");
//            statement.execute("INSERT INTO `test`.`table` (`id`, `firstname`, `lastname`)\n" + "VALUES (3, 'Петров', 'Петр')");
//
//            ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
//            while (set.next()) {
//                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        SessionFactory sessionFactory = new MetadataSources(registry)
//                .buildMetadata().buildSessionFactory();
//        Session session =sessionFactory.openSession();
//        Magic magic = new Magic("Magic arrow", 10, 0);
//        session.beginTransaction();
//        session.save(magic);
//        session.getTransaction().commit();
//        session.close();
        Connector connector = new Connector();
        Session session = connector.getSession();
        Magic magic = new Magic("Magic arrow", 0, 10, 10);
        session.beginTransaction();
        session.save(magic);
        magic = new Magic("stone skin", 10, 4, 0); session.save(magic);
        magic = new Magic("lightning", 5, 6, 0); session.save(magic);
        magic = new Magic("bloodlust", 7, 3, 0); session.save(magic);
        magic = new Magic("slow", 9, 1, 0); session.save(magic);
        session.getTransaction().commit();
        session.close();
    }

    public static void getAllInf() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()){
            List<Magic> books = session.createQuery("FROM Magic ", Magic.class).getResultList();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getByIdInf() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()){
//            List<Magic> books = session.createQuery("FROM Magic ", Magic.class).getResultList();
//            books.forEach(System.out::println);
            String hql = "from Magic where id = :id";
            Query<Magic> query = session.createQuery(hql, Magic.class);
            query.setParameter("id",14);
            Magic magic = query.getSingleResult();
            System.out.println(magic);
            magic.setAttBonus(100);
            magic.setName("Ярость");
            session.beginTransaction();
            session.update(magic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByIdInf() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()){
            Transaction transaction = session.beginTransaction();
            List<Magic> magics = session.createQuery("FROM Magic ", Magic.class).getResultList();
            magics.forEach(session::delete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
