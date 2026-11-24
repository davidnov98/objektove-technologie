module com.example.crudjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.crudjava to javafx.fxml;
    exports com.example.crudjava;
}