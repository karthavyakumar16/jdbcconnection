package com.neoteric.jdbcconnection.jdbcconnection;

import java.util.Date;
import java.util.List;

public class JDBCProject {
    private int id;
    private String pname;
    private Date startDate;
    private Date endDate;
    public List<JDBCEmployee> employeeList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<JDBCEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<JDBCEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "JDBCProject{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employeeList=" + employeeList +
                '}';
    }
}
