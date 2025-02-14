package com.mycompany.databasehandlerlab;
import java.util.*;
import java.sql.*;

public class Student {
    public String studentNumber;
    public String fname;
    public String mname;
    public String lname;
    public String sex;
    public String birth;
    public int start;
    public String department;
    public int units;
    public String address;

    public Student(String studentNumber, String fname, String mname, String lname, String sex, String birth, int start, String department, int units, String address) {
        this.studentNumber = studentNumber;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.sex = sex;
        this.birth = birth;
        this.start = start;
        this.department = department;
        this.units = units;
        this.address = address;
    }    

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" + "studentNumber=" + studentNumber + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", sex=" + sex + ", birth=" + birth + ", start=" + start + ", department=" + department + ", units=" + units + ", address=" + address + '}';
    }
    
}
