package mysql.lecture;

import java.sql.*;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "M131231l";

    public static void con() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
//            statement.execute("DROP SCHEMA `test`");
//            statement.execute("CREATE SCHEMA `test`");
//            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstname` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, PRIMARY KEY (`id`));");
//            statement.execute("INSERT INTO `test`.`table` (`id`, `firstname`, `lastname`)\n" +
//                    "VALUES (1, 'Иванов', 'Ivan')");

            ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
            while (set.next()) {
                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
