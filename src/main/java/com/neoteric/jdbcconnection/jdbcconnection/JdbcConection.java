package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcConection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3307/sonar";
        String username = "root";
        String password = "sonar";
        String query = "select * from sonar.employee";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("connection established successfully");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);


        while (rs.next()) {

            String name = rs.getString("name");
            System.out.println(name);
        }
        st.close();
        con.close();
        System.out.println("connection closed");
    }


}