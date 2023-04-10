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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    ConnectDB connectDB = new ConnectDB();
    FXMLLoader loader;
    Scene scene;
    Stage stage = new Stage();
    @FXML
    private Button Edit;
    private String name;
    Integer index;
    @FXML
    private Button Delete;
    private ObservableList<User> listM = FXCollections.observableArrayList();
    private User admin;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button next01;
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
    public void setAdmin(User user){
        this.admin = user;
        AdminLabel.setText("Admin ID : " +  this.admin.getStu_id() + "\t Name : " + this.admin.getFirstname() + " " + this.admin.getLastname());
        listM = connectDB.getDataUser();
        tableView.setItems(listM);   }
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Controller controller = loader.getController();
        Stage stage2 = (Stage) back.getScene().getWindow();
        stage2.close();
    }
    @FXML
    protected void onNextButton() throws IOException {
        loader = new FXMLLoader(getClass().getResource("add_Student.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        AddStudentController addStudentController = loader.getController();
        addStudentController.setUser(admin);
        Stage stage2 = (Stage) next.getScene().getWindow();
        stage2.close();
    }
    @FXML
    protected void onNextButton01() throws IOException {
        loader = new FXMLLoader(getClass().getResource("add_course.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        AddCourseController addCourseController = loader.getController();
        addCourseController.setUser(admin);
        Stage stage2 = (Stage) next.getScene().getWindow();
        stage2.close();
    }
    @FXML
    protected void getItem(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableView.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return ;
        }
        name = stu_id.getCellData(index).toString();
    }
    @FXML
    protected void Delete(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Alert");
        confirmationAlert.setHeaderText("Are you sure?");
        confirmationAlert.setContentText("Do you want to Delete account " + name);
        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationAlert.getButtonTypes().setAll(okButton, cancelButton);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                String deleteQuery = "DELETE FROM user WHERE student_id = ?";
                var stm = connectDB.getConnection();
                try {
                    PreparedStatement preparedStatement = stm.prepareStatement(deleteQuery);
                    preparedStatement.setString(1,name);
                    preparedStatement.executeUpdate();
                    listM = connectDB.getDataUser();
                    tableView.setItems(listM);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onEditButton(ActionEvent actionEvent) throws IOException {
        loader = new FXMLLoader(getClass().getResource("Edit.fxml"));
        scene = new Scene(loader.load(), 1280, 720);
        stage.setTitle("register application");
        stage.setScene(scene);
        stage.show();
        EditController editController = loader.getController();
        editController.setAdmin(admin);
        for (var e:
             listM) {
            if (e.getStu_id().equals(name)){
                editController.setUserEdit(e);
            }
        }
        Stage stage1 = (Stage) Edit.getScene().getWindow();
        stage1.close();
    }
}
