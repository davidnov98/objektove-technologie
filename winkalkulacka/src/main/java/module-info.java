module com.example.winkalkulacka {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.winkalkulacka to javafx.fxml;
    exports com.example.winkalkulacka;
}