package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddStudentController {
    private User user;
    private FXMLLoader loader;
    private Scene scene;
    private String s_departments;
    private String s_gender;
    private String s_role;
    private Stage stage = new Stage();
    private ConnectDB connectDB = new ConnectDB();
    private Statement statement = connectDB.getStm();
    @FXML
    private ComboBox<String> departmentsCombo = new ComboBox<>();
    @FXML
    private ComboBox<String> genderCombo = new ComboBox<>();
    @FXML
    private ComboBox<String> roleCombo = new ComboBox<>();
    @FXML
    private Button Back;
    @FXML
    private TextField student_ID;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField password;
    @FXML
    private TextArea address;
    @FXML
    private TextField phone;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
    @FXML
    private Label lable;
    @FXML
    protected void onBackButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        AdminController adminController = loader.getController();
        adminController.setAdmin(user);
        Stage stage1 = (Stage) Back.getScene().getWindow();
        stage1.close();
    }
    private ObservableList<String> department = connectDB.getDataDepartments();
    private ObservableList<String> gender = FXCollections.observableArrayList();
    private ObservableList<String> role = FXCollections.observableArrayList();

    public void initialize() {
        gender.add("Female");
        gender.add("Male");
        role.add("Admin");
        role.add("student");
        genderCombo.setItems(gender);
        departmentsCombo.setItems(department);
        roleCombo.setItems(role);
    }
    public void setUser(User user){
        this.user = user;
    }
    @FXML
    public void ComboBoxSelectionDepartments(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        s_departments = comboBox.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void ComboBoxSelectionGender(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        s_gender = comboBox.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void ComboBoxSelectionRole(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        s_role = comboBox.getSelectionModel().getSelectedItem();
    }
    @FXML
    protected void onButtonSubmit() throws SQLException {
        lable.setText("Add Student");
        PreparedStatement statement1 = null;
        if ((!student_ID.getText().equals("")) && (!firstname.getText().equals("")) && (!lastname.getText().equals("")) && (!password.getText().equals("")) && (!address.getText().equals("")) && (s_gender != null) && (s_departments != null) && (s_role != null)){
            System.out.println("inFunction");
            String sql = "INSERT INTO user (student_id, firstname, lastname, password, gender, address, phone, department, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement1 = connectDB.getConnection().prepareStatement(sql);
            statement1.setString(1, student_ID.getText());
            statement1.setString(2, firstname.getText());
            statement1.setString(3, lastname.getText());
            statement1.setString(4, password.getText());
            statement1.setString(5, s_gender);
            statement1.setString(6, address.getText());
            statement1.setString(7, phone.getText());
            statement1.setString(8, s_departments);
            statement1.setString(9, s_role);
            statement1.executeUpdate();
            onButtonCancel();
        }else {
            lable.setText("Error Please fill out all information.");
        }
    }
    @FXML
    protected void onButtonCancel(){
        student_ID.clear();
        firstname.clear();
        lastname.clear();
        password.clear();
        phone.clear();
        address.clear();
        genderCombo.cancelEdit();
        departmentsCombo.cancelEdit();
        roleCombo.cancelEdit();
    }
}
