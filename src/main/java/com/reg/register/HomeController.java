package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    ConnectDB connectDB = new ConnectDB();
    private ObservableList<Enroll> enrolls;
    private ObservableList<Course> courses;
    private ObservableList<Course> courses_user = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Course, String> ID_Course = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> Thai_name = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> Eng_name = new TableColumn<>();
    @FXML
    private TableColumn<Course, String> belong = new TableColumn<>();
    @FXML
    private TableView<Course> table_course;
    @FXML
    Button back;
    @FXML
    Button next;
    @FXML
    Label Student_s;
    @FXML
    Button view;
    Integer index;
    private User user;

    public void setDate(User user){
        this.user = user;
        Student_s.setText("Student-ID : " + this.user.getStu_id() +  "\t FirstName : " + this.user.getFirstname() + "\t LastName : " + this.user.getLastname());
        enrolls = connectDB.getDataEnrolls();
        courses = connectDB.getDataCourse();
        for (var e:
             enrolls) {
            for (var x:
                 courses) {
                if (e.getStudent_ID().equals(user.getStu_id()) && e.getCourse_ID().equals(x.getId_course())){
                    courses_user.add(x);
                }
            }
        }
        table_course.setItems(courses_user);
    }
    public void onBackButton(ActionEvent actionEvent) throws IOException {
        loader = new FXMLLoader(getClass().getResource("view.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Controller controller = loader.getController();
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
    @FXML
    public void onNextButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("course02.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        UserController userController = loader.getController();
        userController.setDate(user);
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID_Course.setCellValueFactory(new PropertyValueFactory<>("id_course"));
        Thai_name.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        Eng_name.setCellValueFactory(new PropertyValueFactory<>("engName"));
        belong.setCellValueFactory(new PropertyValueFactory<>("belong"));
    }
    @FXML
    private void getTable() throws IOException {
        index = table_course.getSelectionModel().getSelectedIndex();
        if (index < 0){
            return;
        }
        loader = new FXMLLoader(getClass().getResource("view01.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        ViewContronlloer viewContronlloer = loader.getController();
        viewContronlloer.setUser(user);
        viewContronlloer.setC_ID(ID_Course.getCellData(index).toString());
        Stage stage2 = (Stage) view.getScene().getWindow();
        stage2.close();
    }
}
