package com.reg.register;

import javafx.beans.property.SimpleStringProperty;

public class Enroll {
    private SimpleStringProperty e_ID;
    private SimpleStringProperty student_ID;
    private SimpleStringProperty course_ID;

    public Enroll(String e_ID, String student_ID, String course_ID) {
        this.e_ID = new SimpleStringProperty(e_ID);
        this.student_ID = new SimpleStringProperty(student_ID);
        this.course_ID = new SimpleStringProperty(course_ID);
    }

    public String getE_ID() {
        return e_ID.get();
    }

    public SimpleStringProperty e_IDProperty() {
        return e_ID;
    }

    public void setE_ID(String e_ID) {
        this.e_ID.set(e_ID);
    }

    public String getStudent_ID() {
        return student_ID.get();
    }

    public SimpleStringProperty student_IDProperty() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID.set(student_ID);
    }

    public String getCourse_ID() {
        return course_ID.get();
    }

    public SimpleStringProperty course_IDProperty() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID.set(course_ID);
    }
}
