package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();

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
        boolean c = true;
        ConnectDB connectDB = new ConnectDB();
        var user = connectDB.getDataUser();
        var dataCourse = connectDB.getDataCourse();
        for (var e:
             user) {
            if (username.getText().equals(e.getStu_id()) && password.getText().equals(e.getPassword())){
                c = false;
                if (e.getRole().equals("Admin")){
                    loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
                    scene = new Scene(loader.load(), 1280, 720);
                    stage.setTitle("register application");
                    stage.setScene(scene);
                    stage.show();
                    AdminController adminController = loader.getController();
                    adminController.setData(user);
                    adminController.setAdmin(e);
                }else {
                    loader = new FXMLLoader(getClass().getResource("course01.fxml"));
                    scene = new Scene(loader.load(), 1280, 720);
                    stage.setTitle("register application");
                    stage.setScene(scene);
                    stage.show();
                    UserConntroller userConntroller = loader.getController();
                    userConntroller.setDate(e);
//                    userConntroller.setDataCourses(dataCourse);
                }
            }
        }
        if (c){
            welcomeText.setText("invalid username or password");
        }else {
            Stage stage2 = (Stage) submit.getScene().getWindow();
            stage2.close();
        }
    }
}