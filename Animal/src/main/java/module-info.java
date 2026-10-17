module com.example.animal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.animal to javafx.fxml;
    exports com.example.animal;
}