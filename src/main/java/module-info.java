module com.reg.register {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.reg.register to javafx.fxml;
    exports com.reg.register;
}