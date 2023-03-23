package com.reg.register;

import javafx.beans.property.SimpleStringProperty;

public class User {
    String stu_id;
    String firstname;
    String lastname;

    String gender;
    String address;
    String phone;
    String department;
    String role;

    public User(String stu_id, String firstname, String lastname, String gender, String address, String phone, String department, String role) {
        this.stu_id = stu_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.role = role;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
