package jdbc;

import java.sql.*;

public class DBConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/usermanagement";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hello12345";

    private static Connection connection = null;

    public static Connection getInstance(){
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (ClassNotFoundException e){
        	e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
