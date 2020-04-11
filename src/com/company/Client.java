package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            System.out.println("Chương trình yêu cầu server xử lý: +,-,x,/ 2 số");
            String num1, num2, op, KQ;
            //nhập từ bàn phím 3 giá trị trên
            Scanner scan = new Scanner(System.in);
            //nhập và kiểm tra số thứ nhất
            while (true) {
                System.out.print("Nhập vào số thứ nhất: ");
                num1 = scan.nextLine();
                try {
                    double so = Double.parseDouble(num1);
                    break;
                } catch (Exception e) {
                    System.out.println("Phải nhập số");
                }
            }
            //nhập và kiểm tra số thứ hai
            while (true) {
                System.out.print("Nhập vào số thứ hai: ");
                num2 = scan.nextLine();
                try {
                    double so = Double.parseDouble(num2);
                    break;
                } catch (Exception e) {
                    System.out.println("Phải nhập số");
                }
            }
            //nhập vào phép toán
            while (true) {
                System.out.print("Nhập vào phép toán (+,-,x,/): ");
                op = scan.nextLine();
                if (op.equals("+") || op.equals("-") || op.equals("x") || op.equals("/"))
                    break;
                else
                    System.out.println("Phải nhập +,-,x,/");
            }
            //gửi lần lượt qua server
            writer.println(num1);
            writer.println(num2);
            writer.println(op);
            //đọc KQ trả về
            KQ = reader.readLine();
            //Xuất KQ ra màn hình
            System.out.println("Kết quả: " + KQ);
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
