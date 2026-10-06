package com.example.semafor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane root;
    semafor s;
    @FXML
    private void initialize() {
        s = new semafor();
        root.getChildren().add(s);
    }
    @FXML private void draw() {
        semafor s = new semafor();
        root.getChildren().add(s);
    }
    @FXML private void changeState() {
        s.zmenStav();
    }
    @FXML protected void startTimer() {
        s.prepni();
    }
    @FXML private void vypniSemafor() {
        s.vypnut();
    }

    @FXML private void ozivSemafor() {
        s.zapnut();
    }

}

