package com.reg.register;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {
    private Stage stage = new Stage();
    private FXMLLoader loader;
    private Scene scene;
    ConnectDB connectDB = new ConnectDB();
    private User admin;
    @FXML
    private TextField ID_course;
    @FXML
    private TextField thai_name;
    @FXML
    private TextField eng_name;
    @FXML
    private Button Back;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;
    @FXML
    private Label label;
    @FXML
    private ComboBox<String> stringComboBox = new ComboBox<>();
    private ObservableList<String> list = connectDB.getFaculties();
    public void setUser(User user) {
        this.admin = user;
    }
    private String belong01;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stringComboBox.setItems(list);
    }
    @FXML
    protected void onBackButton() throws IOException {
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
    @FXML
    protected void onButtonSubmit() throws SQLException {
        PreparedStatement statement1 = null;
        if (ID_course != null && thai_name != null && eng_name != null && belong01 != null){
            String sql = "INSERT INTO course (ID_course, thai_name, eng_name, belong) VALUES (?, ?, ?, ?)";
            statement1 = connectDB.getConnection().prepareStatement(sql);
            statement1.setString(1, ID_course.getText());
            statement1.setString(2, thai_name.getText());
            statement1.setString(3, eng_name.getText());
            statement1.setString(4, belong01);
            statement1.executeUpdate();
            onButtonCancel();
        }else {
            label.setText("Error Please fill out all information.");
        }
    }
    @FXML
    protected void onButtonCancel(){
        ID_course.clear();
        thai_name.clear();
        eng_name.clear();
        belong01 = null;
        stringComboBox.cancelEdit();
    }
    @FXML
    public void ComboBoxSelectionRole(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        belong01 = comboBox.getSelectionModel().getSelectedItem();
    }
}
