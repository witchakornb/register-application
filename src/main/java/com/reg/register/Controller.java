package com.reg.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private void cancel(ActionEvent actionEvent){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void setSubmit(ActionEvent actionEvent) throws SQLException {
        ConnectDB.test();
    }
}