package com.reg.register;

import javafx.beans.property.SimpleStringProperty;

public class Course {
    private SimpleStringProperty c_id;
    private SimpleStringProperty id_course;
    private SimpleStringProperty thaiName;
    private SimpleStringProperty engName;
    private SimpleStringProperty belong;

    public Course(String c_id, String id_course, String thaiName, String engName, String belong) {
        this.c_id = new SimpleStringProperty(c_id);
        this.id_course = new SimpleStringProperty(id_course);
        this.thaiName = new SimpleStringProperty(thaiName);
        this.engName = new SimpleStringProperty(engName);
        this.belong = new SimpleStringProperty(belong);
    }

    public String getC_id() {
        return c_id.get();
    }

    public SimpleStringProperty c_idProperty() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id.set(c_id);
    }

    public String getId_course() {
        return id_course.get();
    }

    public SimpleStringProperty id_courseProperty() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course.set(id_course);
    }

    public String getThaiName() {
        return thaiName.get();
    }

    public SimpleStringProperty thaiNameProperty() {
        return thaiName;
    }

    public void setThaiName(String thaiName) {
        this.thaiName.set(thaiName);
    }

    public String getEngName() {
        return engName.get();
    }

    public SimpleStringProperty engNameProperty() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName.set(engName);
    }

    public String getBelong() {
        return belong.get();
    }

    public SimpleStringProperty belongProperty() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong.set(belong);
    }
}
