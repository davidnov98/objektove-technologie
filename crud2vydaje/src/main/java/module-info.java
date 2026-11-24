module com.example.crud2vydaje {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.crud2vydaje to javafx.fxml;
    exports com.example.crud2vydaje;
}