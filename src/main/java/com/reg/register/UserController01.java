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

public class UserController01 implements Initializable {
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private ObservableList<Course> courses;
    private ObservableList<Course> courses2;
    private ObservableList<Enroll> enrolls;
    @FXML
    private TableColumn<User, String> ID_Course = new TableColumn<>();
    @FXML
    private TableColumn<User, String> Thai_name = new TableColumn<>();
    @FXML
    private TableColumn<User, String> Eng_name = new TableColumn<>();
    @FXML
    private TableColumn<User, String> belong = new TableColumn<>();
    @FXML
    private TableView<Course> table_course;
    @FXML
    Label Student_s;
    @FXML
    Button back;
    @FXML
    Button next;
    @FXML
    Button next02;
    @FXML
    Button add;
    private User user;
    private Integer index;
    ConnectDB connectDB = new ConnectDB();

    public void setDate(User user){
        this.user = user;
        connectData();
        Student_s.setText("Student-ID : " + this.user.getStu_id() +  "\t FirstName : " + this.user.getFirstname() + "\t LastName : " + this.user.getLastname());
    }
    public void setCourses2(ObservableList<Course> courses){
        this.courses2 = courses;
    }
    private void connectData(){
        courses = connectDB.getDataCourse();
        enrolls = connectDB.getDataEnrolls();
        table_course.setItems(courses);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID_Course.setCellValueFactory(new PropertyValueFactory<>("id_course"));
        Thai_name.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        Eng_name.setCellValueFactory(new PropertyValueFactory<>("engName"));
        belong.setCellValueFactory(new PropertyValueFactory<>("belong"));
    }
    @FXML
    protected void onBackButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("course02.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        UserController userController = loader.getController();
        userController.setDate(user);
        userController.setDataCourses(courses2);
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
    public void setTable(){
        ConnectDB connectDB = new ConnectDB();
        this.courses = connectDB.getDataCourse();
        table_course.setItems(this.courses);
    }
    @FXML
    private void setAdd() throws IOException {
        boolean cc = true;
        index = table_course.getSelectionModel().getSelectedIndex();
        if (index < 0){
            return;
        }
        for (var e:
                courses) {
            if (e.getId_course().equals(ID_Course.getCellData(index).toString())){
                for (var c:
                     enrolls) {
                    if (e.getId_course().equals(c.getCourse_ID()) && c.getStudent_ID().equals(user.getStu_id())){
                        cc = false;
                        break;
                    }
                }
                for (var c:
                     courses2) {
                    if (e.getId_course().equals(c.getId_course())){
                        cc = false;
                        break;
                    }
                }
                if (cc){
                    courses2.add(e);
                }
            }
        }
        onBackButton();
    }
    @FXML
    protected void setButtonNext02() throws IOException {
        loader = new FXMLLoader(getClass().getResource("course.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        EnrollController enrollController = loader.getController();
        enrollController.setDate(user);
        enrollController.setDataCourses(courses2);
        Stage stage2 = (Stage) next02.getScene().getWindow();
        stage2.close();
    }
}
