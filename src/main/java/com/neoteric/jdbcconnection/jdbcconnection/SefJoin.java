package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

public class SefJoin {


    public static Collection<JDBCEmployee> getHierarchyList() {
        String url = "jdbc:mysql://localhost:3307/sonar";
        String username = "root";
        String password = "sonar";
        String query =
                "select m.name,e.*from sonar.employee e,sonar.employee m where e.mid=m.id;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("connection established successfully");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {

                

            }
        } catch (Exception e) {

        }
        return null;
    }
}



