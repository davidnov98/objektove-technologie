module com.example.banka {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.banka to javafx.fxml;
    exports com.example.banka;
}