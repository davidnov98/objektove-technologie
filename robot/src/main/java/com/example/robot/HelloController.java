package com.example.robot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane root;
    private robot r;
    @FXML
    private void initialize() {
        r = new robot(600, 400, 130, 190);
        root.getChildren().add(r);

    }

    @FXML
    protected void automat() {
        r.automat();
    }
}
