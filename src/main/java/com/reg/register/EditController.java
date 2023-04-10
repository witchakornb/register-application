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

public class EditController {
    ConnectDB connectDB = new ConnectDB();
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    private ObservableList<String> department = connectDB.getDataDepartments();
    private ObservableList<String> gender = FXCollections.observableArrayList();
    private ObservableList<String> role = FXCollections.observableArrayList();
    private User admin, user;
    @FXML
    private Button Back;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
    @FXML
    private Label label;
    @FXML
    private TextField student_ID, firstname, lastname, password, phone;
    @FXML
    private ComboBox genderCombo, departmentsCombo, roleCombo;
    @FXML
    private TextArea address;
    private String s_departments;
    private String s_gender;
    private String s_role;
    public void onButtonSubmit(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Alert");
        confirmationAlert.setHeaderText("Are you sure?");
        confirmationAlert.setContentText("Do you want to Edit account " + user.getStu_id());
        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationAlert.getButtonTypes().setAll(okButton, cancelButton);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                String Query = "UPDATE user SET student_id = ?,firstname = ?, lastname = ?, password = ?, gender = ?, address = ?, phone = ?, department = ?, role = ?  WHERE student_id = ?";
                var stm = connectDB.getConnection();
                try {
                    PreparedStatement statement = stm.prepareStatement(Query);
                    statement.setString(1, student_ID.getText());
                    statement.setString(2, firstname.getText());
                    statement.setString(3, lastname.getText());
                    statement.setString(4, password.getText());
                    statement.setString(5, s_gender);
                    statement.setString(6, address.getText());
                    statement.setString(7, phone.getText());
                    statement.setString(8, s_departments);
                    statement.setString(9, s_role);
                    statement.setString(10, student_ID.getText());
                    statement.executeUpdate();
                    onBackButton(actionEvent);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onButtonCancel(ActionEvent actionEvent) throws IOException {
        onBackButton(actionEvent);
    }
    public void initialize() {
        gender.add("Female");
        gender.add("Male");
        role.add("Admin");
        role.add("student");
        genderCombo.setItems(gender);
        departmentsCombo.setItems(department);
        roleCombo.setItems(role);
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

    public void onBackButton(ActionEvent actionEvent) throws IOException {
        loader = new FXMLLoader(getClass().getResource("table-view.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        AdminController adminController = loader.getController();
        adminController.setAdmin(admin);
        Stage stage1 = (Stage) Back.getScene().getWindow();
        stage1.close();
    }
    public void setAdmin(User admin){
        this.admin = admin;
    }
    public void setUserEdit(User user){
        this.user = user;
        setData();
    }
    private void setData(){
        student_ID.setText(user.getStu_id());
        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        password.setText(user.getPassword());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        genderCombo.setValue(user.getGender());
        departmentsCombo.setValue(user.getDepartment());
        roleCombo.setValue(user.getRole());
        student_ID.setDisable(true);
        roleCombo.setDisable(true);
    }
}
