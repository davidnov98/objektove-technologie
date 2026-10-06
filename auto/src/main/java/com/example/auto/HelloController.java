package com.example.auto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane root;
    auto a;
    @FXML
    private void initialize() {
        a = new auto(620,340, 40, 20);
        root.getChildren().add(a);

    }

    @FXML
    protected void draw() {
    a.requestFocus();

    }
    @FXML
    private void automat() {
        a.automat();
    }
}
