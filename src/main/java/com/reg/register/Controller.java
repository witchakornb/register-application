package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller{
    @FXML
    private Label welcomeText;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;

    ObservableList<User> listM = FXCollections.observableArrayList();
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
    private void setSubmit(ActionEvent actionEvent) throws IOException{
        ConnectDB connectDB = new ConnectDB();
        var user = connectDB.getdata();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
        Scene scene = new Scene(loader.load(), 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("register application");
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        AdminController adminController = loader.getController();
        System.out.println(adminController);
        adminController.setData(user);
        Stage stage2 = (Stage) submit.getScene().getWindow();
        stage2.close();
    }
}