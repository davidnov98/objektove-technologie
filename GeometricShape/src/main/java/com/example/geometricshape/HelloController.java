package com.example.geometricshape;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class HelloController {
    @FXML
    private Pane root;
    private Timeline t;

    @FXML
    private void initialize() {
        t = new Timeline(new KeyFrame(Duration.millis(100),e->timer()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
    private void timer() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node n = root.getChildren().get(i);

            if (n instanceof GeometricShape) {
                ((GeometricShape)n).pohyb();
            }
        }
    }
    @FXML
    private void farba() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node n = root.getChildren().get(i);
            if (n instanceof MyCircle) {
                ((MyCircle)n).setColor(Color.BLUE);
            }
        }
    }
    @FXML
    private void posun() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node n = root.getChildren().get(i);
            if (n instanceof GeometricShape) {
                int y = ((GeometricShape)n).getY();
                ((GeometricShape)n).setY(y + 30);
            }
        }
    }

    @FXML
    private void pridat() {
        int rx = (int) (Math.random() * (500 - 100 + 1) + 100);
        int ry = (int) (Math.random() * (420 - 100 + 1) + 100);
        int objekt = (int) (Math.random() * (3 - 1 + 1)  + 1);

        GeometricShape gs;
        switch (objekt) {
            case 1:
                gs = new MySquare(rx, ry, Color.RED, 40);
                break;
            case 2:
                gs = new MyRectangle(rx, ry, Color.YELLOW, 40, 30);
                break;
            case 3:
                gs =new MyCircle(rx, ry, Color.GREEN, 40, 40);
                break;
            default:
                return;
        }


        root.getChildren().add(gs);
        gs.vykresli();

    }

}
