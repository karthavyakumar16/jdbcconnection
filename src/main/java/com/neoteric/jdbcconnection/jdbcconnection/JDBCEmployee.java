package com.neoteric.jdbcconnection.jdbcconnection;

import java.util.List;

public class JDBCEmployee {

    private int id;
    private String name;
    private String dept;
    private int salary;
    private int mid;
    private int pid;
    public List<JDBCEmployee> employeeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<JDBCEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<JDBCEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "JDBCEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", mid=" + mid +
                ", pid=" + pid +
                '}';
    }
}
