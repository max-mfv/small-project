package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();
                    PrintWriter writer = new PrintWriter(os,true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String num1, num2, op, KQ;
                    //đọc lần lượt sotien, desloai, resloai
                    num1 = reader.readLine();
                    num2 = reader.readLine();
                    op = reader.readLine();
                    //gọi hàm CHANGE đưa vào KQ
                    KQ = XuLy(num1, num2, op);
                    //gửi KQ qua client  
                    writer.println(KQ);
                    socket.close();
                } catch (IOException e) {
                    System.err.println(" Connection Error: " + e);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
    private static String XuLy(String num1, String num2, String op){
        String KQ = "Không thể tính toán!";
        try{
            double n1 = Double.parseDouble(num1);
            double n2 = Double.parseDouble(num2);
            double kq=0;
            char pt = op.charAt(0);
            switch(pt){
                case '+': kq=n1+n2;KQ = kq+""; break;
                case '-': kq=n1-n2;KQ = kq+""; break;
                case 'x': kq=n1*n2;KQ = kq+""; break;
                case ':': kq=n1/n2;KQ = kq+""; break;
                default:KQ = "Phép toán không hợp lệ!";
            }
        }
        catch(Exception ex){
            KQ = "Không thể tính toán!";
        }
        return KQ;
    }
}
