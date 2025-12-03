package com.pluralsight;

import com.mysql.cj.PreparedQuery;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DataManager {

    private static ArrayList<Shipper> shippers = null;

    public void insertNewShipper(String name, String phone, MysqlDataSource dataSource) {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into shippers (CompanyName,Phone) values (?,?)"
                )
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.executeUpdate();

            System.out.println("Added shipper: " + name + " " + phone);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllShippers(MysqlDataSource dataSource) {
        shippers = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from shippers"
            );
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt("ShipperID");
                    String name = resultSet.getString("CompanyName");
                    String phone = resultSet.getString("Phone");
                    Shipper shipper = new Shipper(id, name, phone);
                    shippers.add(shipper);
                    System.out.println(shipper.getId() + " " + shipper.getName() + " " + shipper.getPhone());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShipperNumber(String phone, int index, MysqlDataSource dataSource) {
        try (
                Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update shippers set Phone = ? where ShipperID = ?;"
            );

            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, index);
            preparedStatement.executeUpdate();
            displayAllShippers(dataSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
