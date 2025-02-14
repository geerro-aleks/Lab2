package com.mycompany.databasehandlerlab;
import java.util.*;
import java.sql.*;

public class DatabaseHandler {   
    public Connection conn;
    public final static String db = "jdbc:sqlite:C:\\Users\\jyroa\\dBeaver\\Lab2\\Lab2";
    static Scanner in = new Scanner(System.in);
    
    public static void main(String args[]){
        DatabaseHandler handler = new DatabaseHandler();
        menu();
        List<Student> students = handler.getStudents();
    if (students.isEmpty()) {
        System.out.println("\nNo students found.");
    } else {
        for (Student student : students) {
            System.out.println();
            System.out.println(student);
        }
    }
    }
    static void menu(){
        System.out.println(""" 
                           Welcome to Student Database Menu! Please enter your number of choice:
                           1. View list of all students
                           2. Add new student
                           3. Find student by student number
                           4. Find student by full name
                           5. View students by start year
                           6. Remove student
                           7. Update student information
                           8. Update student units
                           9. Exit
                           """);
    }
    public void DatabaseHandler(){
        initializeStudents();
    }
    public void initializeStudents(){
        String sql = "DROP TABLE IF EXISTS Students" +
                    "CREATE TABLE Students (" +
                    "student_number TEXT NOT NULL," +
                    "student_fname TEXT NOT NULL," +
                    "student_mname TEXT," +
                    "student_lname TEXT NOT NULL," +
                    "student_sex TEXT NOT NULL," +
                    "student_birth TEXT NOT NULL," +
                    "student_start INTEGER NOT NULL," +
                    "student_department TEXT NOT NULL," +
                    "student_units INTEGER NOT NULL," +
                    "student_address TEXT," +
                    "CONSTRAINT Students_PK PRIMARY KEY (student_number)" +
                    ");";
         try (Connection conn = DriverManager.getConnection(db);Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Student getStudent(String studentNumber){
        String sql = "SELECT * FROM Students s WHERE s.student_number = ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return chooseStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Student getStudent(String studentFname, String studentMname, String studentLname){
        String sql = "SELECT * FROM Students s WHERE s.student_fname = ? AND s.student_mname = ? AND s.student_lname = ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentFname);
            stmt.setString(2, studentMname);
            stmt.setString(3, studentLname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return chooseStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(chooseStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public Boolean removeStudent(String studentNumber){
        String sql = "DELETE FROM Students s WHERE s.student_number = ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Student> getStudentsByYear(int year){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students s WHERE s.student_number LIKE ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, year);  
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(chooseStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public Boolean updateStudentInfo(String studentNumber, Student studentInfo){
        String sql = "UPDATE Students s SET s.student_fname = ?, s.student_mname = ?, s.student_lname = ?, s.student_department = ?, s.student_address = ? WHERE s.student_number = ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentInfo.getFname());
            stmt.setString(2, studentInfo.getMname());
            stmt.setString(3, studentInfo.getLname());
            stmt.setString(4, studentInfo.getDepartment());
            stmt.setString(5, studentInfo.getAddress());
            stmt.setString(6, studentNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean updateStudentUnits(int units, String studentNumber){
        String sql = "UPDATE Students s SET s.student_units = ? WHERE s.student_number = ?";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, units);
            stmt.setString(2, studentNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean insertStudent(Student newStudent){
        String sql = "INSERT INTO Students (student_number, student_fname, student_mname, student_lname, student_sex, student_birth, student_start, student_department, student_units, student_address) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStudent.getStudentNumber());
            stmt.setString(2, newStudent.getFname());
            stmt.setString(3, newStudent.getMname());
            stmt.setString(4, newStudent.getLname());
            stmt.setString(5, newStudent.getSex());
            stmt.setString(6, newStudent.getBirth());
            stmt.setInt(7, newStudent.getStart());
            stmt.setString(8, newStudent.getDepartment());
            stmt.setInt(9, newStudent.getUnits());
            stmt.setString(10, newStudent.getAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Student chooseStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getString("student_number"),
                rs.getString("student_fname"),
                rs.getString("student_mname"),
                rs.getString("student_lname"),
                rs.getString("student_sex"),
                rs.getString("student_birth"),
                rs.getInt("student_start"),
                rs.getString("student_department"),
                rs.getInt("student_units"),
                rs.getString("student_address")
        );
    }
}
