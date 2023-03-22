module com.reg.register {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;


    opens com.reg.register to javafx.fxml;
    exports com.reg.register;
}