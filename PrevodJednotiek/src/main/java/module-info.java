module com.example.prevodjednotiek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prevodjednotiek to javafx.fxml;
    exports com.example.prevodjednotiek;
}