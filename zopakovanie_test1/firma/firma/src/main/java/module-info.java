module com.example.firma {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.firma to javafx.fxml;
    exports com.example.firma;
}