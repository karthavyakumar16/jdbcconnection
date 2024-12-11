package com.neoteric.jdbcconnection.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTreeTest {

        public  static List<JDBCProject> getAllocationEmployees(){

            String url = "jdbc:mysql://localhost:3307/sonar";
            String user = "root";
            String password = "sonar";

            List<JDBCProject> projects = new ArrayList<>();
            Map<Integer, JDBCProject> projectMap = new HashMap<>();
            Map<Integer, JDBCEmployee> employeeMap = new HashMap<>();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database!");

                String query =  "SELECT " +"e.id AS EmployeeID, e.name AS EmployeeName, e.pid AS ProjectId, " + "e.salary, e.dept, " +
                        "m.id AS ManagerID, m.name AS ManagerName, " + "p.pname AS ProjectName, p.startdate, p.enddate " +
                        "FROM sonar.Employee e " + "LEFT JOIN sonar.Employee m ON e.mid = m.id " +"INNER JOIN sonar.project p ON e.pid = p.id";

//             String query = "SELECT e.id AS EmployeeID, e.name AS EmployeeName, m.id AS ManagerID, m.name AS ManagerName\n" +
//                     "FROM sonar.Employee e INNER JOIN sonar.Employee m ON e.mid = m.id";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {

                    System.out.println("Employee ID: " + resultSet.getInt("EmployeeID") +
                            ", Employee Name: " + resultSet.getString("EmployeeName") +
                            ",Employee Salary: " + resultSet.getString("salary")+
                            ", Project Name: " + resultSet.getString("ProjectName") +
                            ", Manager Name: " + resultSet.getString("ManagerName"));

                    int projectId = resultSet.getInt("ProjectId");
                    JDBCProject project = projectMap.getOrDefault(projectId, new JDBCProject());
                    if (!projectMap.containsKey(projectId)) {
                        project.setId(projectId);
                        project.setPname(resultSet.getString("ProjectName"));
                      //  project.setStartDate(resultSet.getDate("startdate").toLocalDate());
                        //project.setEndDate(resultSet.getDate("enddate").toLocalDate());
                        project.setEmployeeList(new ArrayList<>());
                        projectMap.put(projectId, project);
                        projects.add(project);
                    }

                    // Fetch Employee details
                    int employeeId = resultSet.getInt("EmployeeID");
                    JDBCEmployee employee = employeeMap.getOrDefault(employeeId, new JDBCEmployee());
                    if (!employeeMap.containsKey(employeeId)) {
                        employee.setId(employeeId);
                        employee.setName(resultSet.getString("EmployeeName"));
                        employee.setSalary(resultSet.getInt("salary"));
                        employee.setDept(resultSet.getString("dept"));
                        employee.setPid(projectId);
                        employee.setEmployeeList(new ArrayList<>());
                        employeeMap.put(employeeId, employee);
                        project.getEmployeeList().add(employee); // Add employee to project
                    }

                    int managerId = resultSet.getInt("ManagerID");
                    if (managerId != 0) {
                        JDBCEmployee manager = employeeMap.getOrDefault(managerId, new JDBCEmployee());
                        if (!employeeMap.containsKey(managerId)) {
                            manager.setId(managerId);
                            manager.setName(resultSet.getString("ManagerName"));
                            manager.getEmployeeList();
                            employeeMap.put(managerId, manager);
                        }
                        manager.getEmployeeList().add(employee);
                        employee.setMid(managerId);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error occurred while connecting to the database or processing data.");
                e.printStackTrace();
            }
            return projects;
        }
    }



