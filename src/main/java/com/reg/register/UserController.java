package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private Course courseF;
    private ObservableList<Course> courses;
    private ObservableList<Course> courses2 = FXCollections.observableArrayList();
    private ObservableList<Enroll> enrolls;
    @FXML
    private Label searchlabel;
    @FXML
    private TextField search;
    @FXML
    private Button buttonSearch;
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
    Label Student_s;
    @FXML
    Button back;
    @FXML
    Button back01;
    @FXML
    Button submit;
    @FXML
    Button cancel;
    @FXML
    Button next01;
    @FXML
    Button next02;
    Integer index;
    private User user;
    ConnectDB connectDB = new ConnectDB();
    public void setDate(User user){
        this.user = user;
        Student_s.setText("Student-ID : " + this.user.getStu_id() +  "\t FirstName : " + this.user.getFirstname() + "\t LastName : " + this.user.getLastname());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID_Course.setCellValueFactory(new PropertyValueFactory<>("id_course"));
        Thai_name.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        Eng_name.setCellValueFactory(new PropertyValueFactory<>("engName"));
        belong.setCellValueFactory(new PropertyValueFactory<>("belong"));
    }
    public void setDataCourses(ObservableList<Course> courses){
        courses2 = courses;
        table_course.setItems(courses2);
    }
    @FXML
    protected void onBackButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("Home_user.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        HomeController homeController = loader.getController();
        homeController.setDate(user);
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
    @FXML
    protected void setButtonNext01() throws IOException {
        loader = new FXMLLoader(getClass().getResource("course01.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        UserController01 userController01 = loader.getController();
        userController01.setDate(this.user);
        userController01.setCourses2(courses2);
        Stage stage2 = (Stage) next01.getScene().getWindow();
        stage2.close();
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
    private void connectData(){
        courses = connectDB.getDataCourse();
        enrolls = connectDB.getDataEnrolls();
    }
    @FXML
    protected void setButtonSearch(ActionEvent actionEvent){
        String cc = search.getText();
        Boolean c1 = true;
        connectData();
        for (var e:
             this.courses) {
            if (cc.equals(e.getId_course())){
                searchlabel.setText(e.getThaiName());
                courseF = e;
                c1 = false;
                break;
            }
        }
        if (c1){
            searchlabel.setText("no course");
        }
        search.clear();
    }
    @FXML
    protected void setButtonSubmit(){
        if (courseF != null){
            Boolean c = true;
            Boolean c1 = true;
            for (var e:
                    enrolls) {
                if (e.getCourse_ID().equals(courseF.getId_course()) && e.getStudent_ID().equals(user.getStu_id())){
                    searchlabel.setText("Unable to register");
                    c = false;
                    break;
                }
            }
            if (c) {
                if (courses2 == null){
                    courses2.add(courseF);
                    table_course.setItems(courses2);
                }else {
                    for (var e:
                         courses2) {
                        if (e.getId_course().equals(courseF.getId_course())){
                            c1 = false;
                            break;
                        }
                    }
                    if (c1){
                        courses2.add(courseF);
                        table_course.setItems(courses2);
                    }else {
                        searchlabel.setText("Please select a course");
                    }
                }
                searchlabel.setText("");
            }
        }else {
            searchlabel.setText("Please select a course");
        }
        courseF = null;
    }
    @FXML
    protected void setButtonCancel(){
        index = table_course.getSelectionModel().getSelectedIndex();
        if (index < 0){
            return;
        }
        for (var e:
             courses2) {
            if (e.getId_course().equals(ID_Course.getCellData(index).toString())){
                courses2.remove(e);
                break;
            }
        }
    }
}
