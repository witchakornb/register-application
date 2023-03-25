package com.reg.register;

import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private ObservableList<User> listM;
    private User admin;
    @FXML
    private Button back;
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
    @FXML
    private Label AdminLabel;
    public void setData(ObservableList<User> data){
        this.listM = data;
        tableView.setItems(listM);
    }
    public void setAdmin(User user){
        this.admin = user;
        AdminLabel.setText("Admin ID : " +  this.admin.getStu_id() + "\t Name : " + this.admin.getFirstname() + " " + this.admin.getLastname());
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
    @FXML
    protected void onBackButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("view.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        Controller controller = loader.getController();
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
}
