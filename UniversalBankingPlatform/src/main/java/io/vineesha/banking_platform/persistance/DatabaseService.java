package io.vineesha.banking_platform.persistance;

import java.sql.*;
import java.util.Properties;

public class DatabaseService {
    private final String URL = "jdbc:postgresql://localhost:5432/bank";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "interOP@123";
    private static DatabaseService databaseServices = null;

    private Connection connection = null;
    private Statement statement = null;

    private DatabaseService(){
        Properties props = new Properties();
        props.setProperty("user",DB_USER);
        props.setProperty("password",DB_PASSWORD);

        try {
            connection = DriverManager.getConnection(URL, props);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static DatabaseService getInstance(){
        if (databaseServices == null){
            databaseServices = new DatabaseService();
        }
        return databaseServices;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
