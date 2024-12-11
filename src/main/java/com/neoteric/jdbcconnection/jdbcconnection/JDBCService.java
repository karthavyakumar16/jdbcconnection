package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class JDBCService {
    public static Collection<JDBCProject> getjdbcemplist() {


        String url = "jdbc:mysql://localhost:3307/sonar";
        String username = "root";
        String password = "sonar";
        String query = "select * from sonar.employee";

        Collection<JDBCProject> projects = new ArrayList<>();
        Map<String,List<JDBCProject>> projectmap=new HashMap<>();

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection(url, username, password);
//            System.out.println("connection established successfully");
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()) {
//
//                String name = rs.getString("name");
//                System.out.println(name);ng(1));
//                if()
//                    JDBCEmployee employee=new JDBCEmployee();
//                    employee.setName(rs.getString("name"));
//                    jdbcProject.getEmployeeList().add(employee);
//                }else{
//                    JDBCProject jdbcProject=new JDBCProject();
//                    jdbcProject.setId(rs.getInt(2));
//                    List<JDBCEmployee>jdbcEmployees=new ArrayList<>();
//                    jdbcProject.setEmployeeList(jdbcEmployees);
//
//                    jdbcProject.setPname(rs.getString(2));
//                    JDBCEmployee employee=new JDBCEmployee();
//                    employee.setName(rs.getString("name"));
//                    jdbcEmployees.add(employee);
//
//                }








           // }
           // List<JDBCProject> projects = new ArrayList<>();

            Map<String, Optional<JDBCEmployee>> maxSalaryByDept = projects.stream().flatMap(project ->
                            project.getEmployeeList().stream())
                    .collect(Collectors.groupingBy(JDBCEmployee::getDept,
                            Collectors.maxBy(Comparator.comparingDouble(JDBCEmployee::getSalary))));

            maxSalaryByDept.forEach((dept, employee) -> {

                System.out.println("Department: " + dept +
                        ", Employee: " + employee.get().getName() +
                        ", MaxSalary: " + employee.get().getSalary());
            });
            Map<String, Optional<JDBCEmployee>> minSalaryByDept = projects.stream().flatMap(project ->
                            project.getEmployeeList().stream())
                    .collect(Collectors.groupingBy(JDBCEmployee::getDept,
                            Collectors.minBy(Comparator.comparingDouble(JDBCEmployee::getSalary))));

            minSalaryByDept.forEach((dept, employee) -> {

                System.out.println("Department: " + dept +
                        ", Employee: " + employee.get().getName() +
                        ", minSalary: " + employee.get().getSalary());
            });


            Map<String, Double> departmentAverageSalary = projects.stream()
                    .flatMap(project -> project.getEmployeeList().stream())
                    .collect(Collectors.groupingBy(
                            JDBCEmployee::getDept,
                            Collectors.averagingDouble(JDBCEmployee::getSalary)
                    ));


      System.out.println("Department Average Salary: " + departmentAverageSalary);

return null;

        }

}


