module com.example.nakupnyzozanm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nakupnyzozanm to javafx.fxml;
    exports com.example.nakupnyzozanm;
}