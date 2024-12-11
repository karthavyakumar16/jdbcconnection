//package com.neoteric.jdbcconnection.jdbcconnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cjdbcconnection {
//
//    public static List<JDBCProject> getProjectAllocationEmployees() {
//
//            String url = "jdbc:mysql://localhost:3307/sonar";
//            String user = "root";
//            String password = "sonar";
//
//            List<JDBCProject> projects = new ArrayList<>();
//
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                try (Connection connection = DriverManager.getConnection(url, user, password)) {
//                    System.out.println("Connected to the database!");
//
//                    String query = "select* from project p CROSS JOIN Employee e where p.id=e.pid";
//
//                    try (PreparedStatement statement = connection.prepareStatement(query);
//                         ResultSet resultSet = statement.executeQuery()) {
//
//                        while (resultSet.next()) {
//                            System.out.println("Processing row: Project - " + resultSet.getString("pname"));
//
//                            int projectId = resultSet.getInt("id");
//                            String projectName = resultSet.getString("pname");
//
//                            JDBCProject project = projects.stream()
//                                    .filter(p -> p.getId() == projectId)
//                                    .findFirst()
//                                    .orElseGet(() -> {
//                                        JDBCProject newProject = null;
//                                        try {
//                                            newProject = new JDBCProject(
//                                                    projectId,
//                                                    projectName,
//                                                    resultSet.getDate("startdate"),
//                                                    resultSet.getDate("enddate"),
//                                                    new ArrayList<>()
//                                            );
//                                        } catch (SQLException e) {
//                                            throw new RuntimeException(e);
//                                        }
//                                        projects.add(newProject);
//                                        return newProject;
//                                    });
//
//                            project.getEmployeeList().add(new Employee(
//                                    resultSet.getInt("id"),
//                                    projectId,
//                                    resultSet.getString("name"),
//                                    resultSet.getString("dept"),
//                                    resultSet.getDouble("salary")
//                            ));
//                        }
//
//                        // Print all projects and their employees
//                        projects.forEach(project -> {
//                            System.out.println("Project: " + project.getPname());
//                            project.getEmployeeList().forEach(employee ->
//                                    System.out.println("  Employee: " + employee.getName() +
//                                            ", Dept: " + employee.getDept() +
//                                            ", Salary: " + employee.getSalary()));
//                        });
//                    }
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return projects;
//        }
//
//    }
//
//
//
