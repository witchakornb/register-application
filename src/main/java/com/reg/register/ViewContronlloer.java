package com.reg.register;

import javafx.collections.FXCollections;
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

public class ViewContronlloer implements Initializable {
    private String C_ID;
    private ConnectDB connectDB = new ConnectDB();
    private ObservableList<Enroll> enrolls;
    private ObservableList<User> users;
    private ObservableList<User> users01 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> Stu_id = new TableColumn<>();
    @FXML
    private TableColumn<User, String> f_name = new TableColumn<>();
    @FXML
    private TableColumn<User, String> l_name = new TableColumn<>();
    @FXML
    private TableColumn<User, String> department = new TableColumn<>();
    @FXML
    private TableView<User> table_course;
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private User user;
    @FXML
    private Button Back;
    @FXML
    Label label;

    public void setUser(User user) {
        this.user = user;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
        label.setText(c_ID);
        enrolls = connectDB.getDataEnrolls();
        users = connectDB.getDataUser();
        for (var e:
             enrolls) {
            for (var x:
                 users) {
                if (c_ID.equals(e.getCourse_ID())){
                    if (x.getStu_id().equals(e.getStudent_ID())){
                        users01.add(x);
                    }
                }
            }
        }
        table_course.setItems(users01);
    }

    @FXML
    private void onButtonBack() throws IOException {
        loader = new FXMLLoader(getClass().getResource("Home_user.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        HomeController homeController = loader.getController();
        homeController.setDate(user);
        Stage stage2 = (Stage) Back.getScene().getWindow();
        stage2.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stu_id.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        f_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        l_name.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
    }
}
