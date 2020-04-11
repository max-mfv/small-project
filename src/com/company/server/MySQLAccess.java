package com.company.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class MySQLAccess {
    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static final String userNameDB = "pocket_web";
    private static final String passwordDB = "";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public static Boolean login(String userName, String password) throws Exception {
        Boolean match = false;

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(jdbcDriver);
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/bank_atm", userNameDB, passwordDB);
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String sql = MessageFormat.format("select * from bank_atm.accounts where name=\"{0}\"", userName);
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String passwordDB = resultSet.getString(7);
                if (password.equals(passwordDB)) {
                    match = true;
                }
            }
            close();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return match;
    }

    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
    }
}
