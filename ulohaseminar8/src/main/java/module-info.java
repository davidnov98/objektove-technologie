module com.example.ulohaseminar8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.ulohaseminar8 to javafx.fxml;
    exports com.example.ulohaseminar8;
}