package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrepareQuerySerivce {

    public static List<JDBCEmployee> getEmployeesByDeptAndSalary(String dept, int salary) {
        String url = "jdbc:mysql://localhost:3307/sonar";
        String user = "root";
        String password = "sonar";

        List<JDBCEmployee> employees = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM sonar.employee WHERE dept = ? AND salary >= ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dept);
            statement.setInt(2, salary);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int managerId = resultSet.getInt("mid");

                int employeeId = resultSet.getInt("id");
                String employeeName = resultSet.getString("name");
                int employeeSalary = resultSet.getInt("salary");
                String employeeDept = resultSet.getString("dept");
                int projectId = resultSet.getInt("pid");

                JDBCEmployee employee = new JDBCEmployee();
                employee.setId(employeeId);
                employee.setName(employeeName);
                employee.setSalary(employeeSalary);
                employee.setDept(employeeDept);
                employee.setPid(projectId);
                employee.setMid(managerId);

                employees.add(employee);
            }

        } catch (Exception e) {
            System.out.println("Error occurred while connecting to the database or processing data.");
            e.printStackTrace();
        }

        return employees;
    }

    public static void main(String[] args) {
        List<JDBCEmployee> employees = getEmployeesByDeptAndSalary("it", 26000);

        System.out.println("Employees List:");
        for (JDBCEmployee employee : employees) {
            System.out.println(employee.getName() + " (" + employee.getId() + ") " +
                    "Dept: " + employee.getDept() + " Salary: " + employee.getSalary());
        }
    }
}

