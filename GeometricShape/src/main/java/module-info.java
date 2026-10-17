module com.example.geometricshape {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.geometricshape to javafx.fxml;
    exports com.example.geometricshape;
}