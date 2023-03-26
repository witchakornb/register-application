package com.reg.register;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    private final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/reg-system";
    private final String username = "root";
    private final String password = "123456789";
    private Connection connection;
    private Statement statement;


    public ConnectDB() {
        this.connection = null;
        this.statement = null;
        connect();
    }

    private void connect(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Connecting to a database...");
            connection = DriverManager.getConnection(DB_URL, username, password);
            statement = connection.createStatement();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Statement getStm(){
        return  statement;
    }
    public Connection getConnection(){
        return connection;
    }
    public ObservableList<User> getDataUser(){
        ObservableList<User> list = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                list.add(new User(rs.getString("ID"), rs.getString( "student_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getString("gender"), rs.getString("address"), rs.getString("phone"), rs.getString("department"), rs.getString("role")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public ObservableList<Course> getDataCourse(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM course";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                courses.add(new Course(resultSet.getString("C_ID"), resultSet.getString("ID_course"), resultSet.getString("Thai_name"), resultSet.getString("Eng_name"), resultSet.getString("belong")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return courses;
    }
    public ObservableList<String> getDataDepartments(){
        ObservableList<String> departments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM department";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                departments.add(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return departments;
    }
    public ObservableList<Enroll> getDataEnrolls(){
        ObservableList<Enroll> enrolls = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM enroll";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                enrolls.add(new Enroll(resultSet.getString("E_ID"), resultSet.getString("student_ID"), resultSet.getString("course_ID")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return enrolls;
    }
    public ObservableList<String> getFaculties(){
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Faculties";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
