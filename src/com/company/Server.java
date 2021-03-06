package com.company;

import com.company.model.Account;
import com.company.server.MySQLAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public final static int SERVER_PORT = 8002;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            // Khởi tạo Socket
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");

            while (true) {
                try {
                    // Client kết nối đến server
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();

                    // Tạo Writer, Reader
                    PrintWriter writer = new PrintWriter(os,true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    // Lấy dữ liệu từ Client
                    String action = reader.readLine();
                    System.out.printf(action);

                    // Process dữ liệu trong hàm Perform
                    Perform(action, writer, reader);
                    socket.close();
                } catch (IOException e) {
                    System.err.println(" Connection Error: " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    private static void Perform(String type, PrintWriter writer, BufferedReader reader) throws Exception {
            int action = Integer.parseInt(type);
            switch (action) {
                case 0:
                    PerformLogin(writer, reader);
//                    break;
                case 2:
                    System.out.println("Change password");
                    PerformChangePassword(writer, reader);
//                    break;
                case 3:
                    System.out.println("Withdraw");
                    PerformCase3(writer, reader);
//                    break;
                case 4:
                    System.out.println("Deposit");
                    PerformCase4(writer, reader);
//                    break;
                case 5:
                    System.out.println("Transfer");
                    PerformCase5(writer, reader);
//                    break;
                default:
                    System.out.println("Không làm gì");
            }
    }

    private static void PerformLogin(PrintWriter writer, BufferedReader reader) throws Exception {
        // Lấy dữ liệu từ Client
        String accountID = reader.readLine();
        String password = reader.readLine();

        Account account = MySQLAccess.login(accountID, password);

        if (account.getAccountNo() == null) {
            writer.println("fail");
        } else {
            writer.println("success");

            // Response client AccountNo và Số dư (Amount)
            writer.println(account.getAccountNo());
            writer.println(account.getAmount());
        }
    }


    private static void PerformChangePassword(PrintWriter writer, BufferedReader reader) throws IOException, SQLException, ClassNotFoundException {
        // Lấy dữ liệu từ client
        String action = reader.readLine();
        String accountID = reader.readLine();
        String newPassword = reader.readLine();

        if(MySQLAccess.changePassword(accountID, newPassword)) {
            writer.println("success");
        } else {
            writer.println("fail");
        }
    }

    private static String PerformCase3(PrintWriter writer, BufferedReader reader) {
        return "";
    }

    private static String PerformCase4(PrintWriter writer, BufferedReader reader) {
        return "";
    }

    private static String PerformCase5(PrintWriter writer, BufferedReader reader) {
        return "";
    }
}
