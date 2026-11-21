module com.example.filmy_zanre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.filmy_zanre to javafx.fxml;
    exports com.example.filmy_zanre;
}