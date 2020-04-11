package com.company.server;

import java.sql.*;
import java.text.MessageFormat;

public class MySQLAccess {
    private static final String userNameDB = "pocket_web";
    private static final String passwordDB = "";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public static Boolean login(String userName, String password) throws Exception {
        Boolean match = false;
        String sql = MessageFormat.format("select * from bank_atm.accounts where account_number=\"{0}\" and password=\"{1}\"", userName, password);
        ResultSet resultSet = requestDB(sql);
        if (resultSet.next()) {
            match = true;
        }
        return match;
    }

    private static ResultSet requestDB(String query) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connect = null;
        Statement statement = null;

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(jdbcDriver);
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/bank_atm", userNameDB, passwordDB);
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return  resultSet;
    }
}
