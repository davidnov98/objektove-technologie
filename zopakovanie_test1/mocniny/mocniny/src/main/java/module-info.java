module com.example.mocniny {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mocniny to javafx.fxml;
    exports com.example.mocniny;
}