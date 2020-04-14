package com.company.client;

import com.company.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginService {
    public static Boolean loginFunction(PrintWriter writer, BufferedReader reader) throws IOException {
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

        // Write data để gửi lên server
        writer.println(0);
        writer.println(account);
        writer.println(password);

        // Lấy data từ server
        result = reader.readLine();
        if (result.equals("success")) {
            return true;
        }
        return false;
    }
}
