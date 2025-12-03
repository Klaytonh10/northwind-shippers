package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class DataManager {

    public void insertNewShipper(String name, String phone, MysqlDataSource dataSource) {

        try(
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
}
