module com.example.zoznamucasnikov {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoznamucasnikov to javafx.fxml;
    exports com.example.zoznamucasnikov;
}