package com.reg.register;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private ObservableList<User> listM;
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
    public void setData(ObservableList<User> data){
        this.listM = data;
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
