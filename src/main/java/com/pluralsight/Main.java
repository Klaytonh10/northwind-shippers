package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static DataManager dataManager = new DataManager();

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUser("root");
        dataSource.setPassword("yearup");

        System.out.println("Input the following shipper data:");
        System.out.print("Shipper Name: ");
        String name = scanner.nextLine();
        System.out.println("Shipper phone number: ");
        String number = scanner.nextLine();
        dataManager.insertNewShipper(name, number, dataSource);

    }
}
