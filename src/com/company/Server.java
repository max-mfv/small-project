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
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");

            Boolean login = MySQLAccess.login("manh", "asdf");
            System.out.println(login);

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();
                    PrintWriter writer = new PrintWriter(os,true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    String type, userName, password, result;

                    type = reader.readLine();
                    userName = reader.readLine();
                    password = reader.readLine();
                    result = Perform(type, userName, password);
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

    private static String Perform(String type, String userName, String password) {
        String result = "Không hợp lệ";
        try {
            int action = Integer.parseInt(type);
            switch (action) {
                case 0:
                    System.out.println("Login");
                    result = "Login";
                    break;
                case 1:
                    System.out.println("Remain amount");
                    result = "Remain amount";
                    break;
                case 2:
                    System.out.println("Change password");
                    result = "Change password";
                    break;
                case 3:
                    System.out.println("Withdraw");
                    result = "Withdraw";
                    break;
                case 4:
                    System.out.println("Deposit");
                    result = "Deposit";
                    break;
                case 5:
                    System.out.println("Transfer");
                    result = "Transfer";
                    break;
                default:
                    result = "Không hợp lệ";
            }

        } catch (Exception ex) {
            result = "Không hợp lệ";
        }
        return result;
    }
}
