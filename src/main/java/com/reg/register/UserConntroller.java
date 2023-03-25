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

public class UserConntroller implements Initializable {
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private ObservableList<Course> courses;
    private ObservableList<Enroll> enrolls;
    private ObservableList<Course> courses2 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, String> C_ID = new TableColumn<>();
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
    private User user;
    private void connectData(){
        ConnectDB connectDB = new ConnectDB();
        enrolls = connectDB.getDataEnrolls();
        courses = connectDB.getDataCourse();
        for (var e:
             enrolls) {
            if (e.getStudent_ID().equals(user.getStu_id())){
                for (var x:
                        courses) {
                    if (e.getCourse_ID().equals(x.getId_course())){
                        courses2.add(x);
                    }
                }
            }
        }
        table_course.setItems(courses2);
    }
    public void setDate(User user){
        this.user = user;
        Student_s.setText("Student-ID : " + this.user.getStu_id() +  "\t FirstName : " + this.user.getFirstname() + "\t LastName : " + this.user.getLastname());
        connectData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        C_ID.setCellValueFactory(new PropertyValueFactory<>("c_id"));
        ID_Course.setCellValueFactory(new PropertyValueFactory<>("id_course"));
        Thai_name.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        Eng_name.setCellValueFactory(new PropertyValueFactory<>("engName"));
        belong.setCellValueFactory(new PropertyValueFactory<>("belong"));
    }
    public void setDataCourses(ObservableList<Course> courses){
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
    @FXML
    protected void onNextButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("course.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        UserConntroller01 userConntroller01 = loader.getController();
        userConntroller01.setTable();
        userConntroller01.setDate(user);
        Stage stage2 = (Stage) next.getScene().getWindow();
        stage2.close();
    }
}
