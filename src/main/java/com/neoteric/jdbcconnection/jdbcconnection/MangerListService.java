package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MangerListService {
    public static List<JDBCEmployee> getEmployeeManagerHierarchy() {
        String url = "jdbc:mysql://localhost:3307/sonar";
        String user = "root";
        String password = "sonar";

        List<JDBCEmployee> managerList = new ArrayList<>();
        List<JDBCEmployee> employeeList = new ArrayList<>();
        Map<Integer, JDBCEmployee> employeeMap = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = " select  m.name ,e. * from sonar.employee e,sonar.employee m  where e.mid = m.id";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int managerId = resultSet.getInt("mid");
                String managerName = resultSet.getString("name");

                int employeeId = resultSet.getInt("id");
                String employeeName = resultSet.getString("name");
                int employeeSalary = resultSet.getInt("salary");
                String employeeDept = resultSet.getString("dept");
                int projectId = resultSet.getInt("pid");

                JDBCEmployee manager = employeeMap.getOrDefault(managerId, new JDBCEmployee());
                if (!employeeMap.containsKey(managerId)) {
                    manager.setId(employeeId);
                    manager.setMid(managerId);
                    manager.setPid(projectId);
                    manager.setName(managerName);
                    manager.setDept(employeeDept);
                    manager.setSalary(employeeSalary);
                    manager.setEmployeeList(new ArrayList<>());
                    managerList.add(manager);
                    employeeMap.put(managerId, manager);
                }

                JDBCEmployee employee = new JDBCEmployee();
                employee.setId(employeeId);
                employee.setName(employeeName);
                employee.setSalary(employeeSalary);
                employee.setDept(employeeDept);
                employee.setPid(projectId);
                employee.setMid(managerId);

                manager.getEmployeeList().add(employee);
                employeeList.add(employee);
            }

        } catch (Exception e) {
            System.out.println("Error occurred while connecting to the database or processing data.");
            e.printStackTrace();
        }

        System.out.println("Managers List:");
        for (JDBCEmployee manager : managerList) {
            System.out.println(manager.getName() + " (" + manager.getId() + ")");
        }

        System.out.println("Employees List:");
        for (JDBCEmployee employee : employeeList) {
            System.out.println(employee.getName() + " (" + employee.getId() + ")");
        }

        return managerList;
    }

    public static void main(String[] args) {
  List<JDBCEmployee> employees =      MangerListService.getEmployeeManagerHierarchy();

  for(JDBCEmployee employee :employees){
      System.out.println(employee);
  }



    }
}

