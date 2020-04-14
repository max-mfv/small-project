package com.company.client;

import com.company.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChangePasswordService {
    public static Boolean changePassword(Account account, PrintWriter writer, BufferedReader reader) throws IOException {
        String newPpassword, reNewPassword, result;

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("Nhập Password mới: ");
            newPpassword = scan.nextLine();
            if (newPpassword.length() > 0) {
                break;
            } else {
                System.out.println("Phải nhập");
            }
        }

        // Write data để gửi lên server
        // Action thực hiện
        writer.println(2);

        writer.println(account.getAccountNo());
        writer.println(newPpassword);

        // Lấy data từ server
        result = reader.readLine();
        if (result.equals("success")) {
            System.out.println("Thay đổi mật khẩu thành công!");
            return true;
        }
        System.out.println("Không thành công xin thử lại!");
        return false;
    }
}
