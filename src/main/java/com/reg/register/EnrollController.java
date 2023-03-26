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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EnrollController implements Initializable {
    FXMLLoader loader;
    ConnectDB connectDB = new ConnectDB();
    Scene scene;
    Stage stage = new Stage();
    private ObservableList<Course> courses2 = FXCollections.observableArrayList();
    private User user;
    @FXML
    Label Student_s;
    @FXML
    Button submit;
    @FXML
    Button cancel;
    @FXML
    Button next01;
    @FXML
    Button back;
    @FXML
    Button back01;
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
    private void setSubmit(){
        if (courses2 != null){
            var stmt = connectDB.getStm();
            try {
                for (var e:
                        courses2) {
                    String sql = "INSERT INTO enroll (student_ID, course_ID) VALUES ('" + user.getStu_id() + "', '" + e.getId_course() +"')";
                    stmt.executeUpdate(sql);
                    setCancel();
                }
            }catch (SQLException e) {
                e.printStackTrace();} finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void setCancel(){
        courses2 = null;
        table_course.setItems(courses2);
        courses2 = FXCollections.observableArrayList();
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
}
