package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 8000;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            PrintWriter writer = new PrintWriter(os, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            System.out.println("Welcome to ABC banking!");
            String account, password, result;

            Scanner scan = new Scanner(System.in);

            while (true) {
                System.out.print("Nhập Account: ");
                account = scan.nextLine();
                if (account.length() > 0) {
                    break;
                } else {
                    System.out.println("Phải nhập");
                }
            }
            while (true) {
                System.out.print("Nhập Password: ");
                password = scan.nextLine();
                if (password.length() > 0)
                    break;
                else
                    System.out.println("Phải nhập");
            }
            writer.println(0);
            writer.println(account);
            writer.println(password);

            result = reader.readLine();
            System.out.println("Kết quả: " + result);
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
