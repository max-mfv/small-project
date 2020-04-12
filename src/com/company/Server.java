package com.company;

import com.company.server.MySQLAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int SERVER_PORT = 8000;

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
                    String action, userName, password, result;

                    action = reader.readLine();
                    userName = reader.readLine();
                    password = reader.readLine();

                    // Process dữ liệu hàm Perform
                    result = Perform(action, userName, password);

                    // Gửi dữ liệu về cho Client
                    writer.println(result);

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

    private static String Perform(String type, String AccountID, String Password) {
        String result = "Không hợp lệ";
        try {
            int action = Integer.parseInt(type);
            switch (action) {
                case 0:
                    result = PerformLogin(AccountID, Password);
                    break;
                case 1:
                    System.out.println("Remain amount");
                    result = PerformCase1(AccountID, Password);
                    break;
                case 2:
                    System.out.println("Change password");
                    result = PerformCase2(AccountID, Password);
                    break;
                case 3:
                    System.out.println("Withdraw");
                    result = PerformCase3(AccountID, Password);
                    break;
                case 4:
                    System.out.println("Deposit");
                    result = PerformCase4(AccountID, Password);
                    break;
                case 5:
                    System.out.println("Transfer");
                    result = PerformCase5(AccountID, Password);
                    break;
                default:
                    result = "Không hợp lệ";
            }

        } catch (Exception ex) {
            result = "Không hợp lệ";
        }
        return result;
    }

    private static String PerformLogin(String AccountID, String Password) throws Exception {
        if (MySQLAccess.login(AccountID, Password)) {
            return "success";
        } else {
            return "fail";
        }
    }

    private static String PerformCase1(String AccountID, String Password) {
        return "";
    }

    private static String PerformCase2(String AccountID, String Password) {
        return "";
    }

    private static String PerformCase3(String AccountID, String Password) {
        return "";
    }

    private static String PerformCase4(String AccountID, String Password) {
        return "";
    }

    private static String PerformCase5(String AccountID, String Password) {
        return "";
    }
}
