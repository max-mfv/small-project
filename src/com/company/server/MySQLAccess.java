package com.company.server;

import com.company.model.Account;

import java.sql.*;
import java.text.MessageFormat;

public class MySQLAccess {
    private static final String userNameDB = "pocket_web";
    private static final String passwordDB = "";
    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public static Account login(String userName, String password) throws Exception {
        Account account = new Account();

        // Tạo câu SQL query
        String sql = MessageFormat.format("select * from bank_atm.accounts where account_number=\"{0}\" and password=\"{1}\"", userName, password);
        ResultSet resultSet = requestDB(sql);

        if (resultSet.next()) {
            account.setAccountNo(resultSet.getString(1));
            account.setAmount(resultSet.getDouble(6));
        }
        return account;
    }

    private static ResultSet requestDB(String query) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        Connection connect = null;
        Statement statement = null;

        try {
            Class.forName(jdbcDriver);
            // Lêt nối với MySql database
            connect = DriverManager.getConnection("jdbc:mysql://localhost/bank_atm", userNameDB, passwordDB);
            statement = connect.createStatement();

            // Chạy câu lệnh SQL
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return  resultSet;
    }
}
