package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sun.jdi.connect.spi.Connection;

import java.sql.SQLException;

public class DataManager {

    public void insertNewShipper(MysqlDataSource dataSource) {

        try(
                Connection connection = dataSource.getConnection()
        ) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
