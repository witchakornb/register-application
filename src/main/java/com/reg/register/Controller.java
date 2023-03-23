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

public class Controller implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;
    @FXML
    private TableColumn<User, String> address = new TableColumn<>();

    @FXML
    private TableColumn<User, String> department = new TableColumn<>();

    @FXML
    private TableColumn<User, String> firstname = new TableColumn<>();

    @FXML
    private TableColumn<User, String> gender = new TableColumn<>();

    @FXML
    private TableColumn<User, String> lastname = new TableColumn<>();

    @FXML
    private TableColumn<User, String> phone = new TableColumn<>();

    @FXML
    private TableColumn<User, String> role = new TableColumn<>();

    @FXML
    private TableColumn<User, String> stu_id = new TableColumn<>();

    @FXML
    private TableView<User> tableView;

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
    private void setSubmit(ActionEvent actionEvent) throws SQLException, IOException {
        ConnectDB connectDB = new ConnectDB();
        Stage stage1 = (Stage) submit.getScene().getWindow();
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        listM = connectDB.getdata();
//        System.out.println(listM);
        tableView.setItems(listM);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stu_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

    }
}