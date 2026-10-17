package com.example.animal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.sql.Time;

public class HelloController {
    @FXML
    private Pane root;
    @FXML
    private Timeline t;
    boolean bezi = false;
    @FXML
    private void initialize() {
        t = new Timeline(new KeyFrame(Duration.millis(100), e->timer()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        bezi = true;
    }
    private void timer() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node n = root.getChildren().get(i);
            if (n instanceof Animal)
                ((Animal)n).pohyb();
        }
    }

    @FXML
    protected void pridaj(Animal an) {
        root.getChildren().add(an);
        an.kresli();
    };
    @FXML
    protected void pridajCarnivore() {
        pridaj(new Carnivore());
    }
    @FXML protected void pridajHerbivore() {
        pridaj(new Herbivore());
    }

    @FXML
    protected void zastav() {
        if (bezi) {
            bezi = false;
            t.stop();
        }else {
            bezi = true;
            t.play();
        }
    }
    @FXML
    protected void otoc() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node n = root.getChildren().get(i);
            if (n instanceof Carnivore)
                ((Carnivore)n).dalsiPohyb();
        }
    }
}
