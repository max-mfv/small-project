package com.company;

import com.company.client.ChangePasswordService;
import com.company.model.Account;
import com.company.client.LoginService;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 8002;

    private static Account account = new Account();
    private static Socket socket = null;

    public static void main(String[] args) throws IOException {
        try {
            // Connect to server
            // Khởi tạo connect đến server
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected: " + socket);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // Writer
            PrintWriter writer = new PrintWriter(os, true);
            // Reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // In ra màn hình
            System.out.println("Welcome to ABC banking!");

            if (LoginService.loginFunction(writer, reader)) {
                System.out.println("Login thành công");

                // Lấy Account ID từ server trả về
                account.setAccountNo(reader.readLine());
                account.setAmount(Double.parseDouble(reader.readLine()));
                renderMenuFunction(writer, reader);
            } else {
                System.out.println("Sai Account hoặc Password");
            }
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private static void renderMenuFunction(PrintWriter writer, BufferedReader reader) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {

            // Disconnect socket nếu account id null
            if (account.getAccountNo() == null) {
                break;
            }

            int select;
            System.out.println("Xin mời chọn chức năng: ");
            System.out.println("1 - Xem số dư");
            System.out.println("2 - Đổi mật khẩu");
            System.out.println("3 - Rút tiền");
            System.out.println("4 - Nạp tiền");
            System.out.println("5 - Chuyển tiền");
            System.out.println("6 - Thoát");
            while (true) {
                System.out.print("Mời chọn: ");
                select = Integer.parseInt(scan.nextLine());
                if (select > 0 && select <= 6) {
                    break;
                } else {
                    System.out.println("Mời chọn lại: ");;
                }
            }
            switch (select) {
                case 1:
                    System.out.println("Số dư hiện tại của bạn là: " + account.getAmount());
                    break;
                case 2:
                    System.out.println("Đổi mật khẩu: ");
                    ChangePasswordService.changePassword(account, writer, reader);
                    break;
                case 3:
                    System.out.println("Rút tiền");
                    break;
                case 4:
                    System.out.println("Nạp tiền");
                    break;
                case 5:
                    System.out.println("Chuyển tiền");
                    break;
                default:
                    account.setAccountNo(null);
                    break;
            }
            while (true) {
                System.out.println("Enter để quay về menu");
                String enter = scan.nextLine();
                if (enter.equals("")) {
                    break;
                }
            }
        }
    }
}
