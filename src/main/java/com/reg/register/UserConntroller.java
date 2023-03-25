package com.reg.register;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserConntroller {
    @FXML
    Label Student_s;
    private User user;
    public void setDate(User user){
        this.user = user;
        Student_s.setText("Student-ID : " + this.user.getStu_id() +  "\t FirstName : " + this.user.getFirstname() + "\t LastName : " + this.user.getLastname());
    }
}
