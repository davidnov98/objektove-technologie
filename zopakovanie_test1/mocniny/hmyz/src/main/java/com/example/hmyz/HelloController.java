package com.example.hmyz;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private Pane root;
    private Timeline t;

    @FXML
    private void posun(KeyEvent k) {
        KeyCode e = k.getCode();
        if (e == KeyCode.W) {

            for (int i = 0; i < root.getChildren().size(); ++i) {
                Node d = root.getChildren().get(i);
                if (d instanceof Hmyz) {

                    Hmyz z = ((Hmyz)d);
                    z.moveYpos(50);
                }
            }
        }
    }


    private void casPohyb() {
        for (int i = 0; i < root.getChildren().size(); ++i) {
            Node d = root.getChildren().get(i);
            if (d instanceof Hmyz) {
                ((Hmyz)d).pohyb();
            }
        }
    }
    @FXML
    private void initialize() {
        t = new Timeline(new KeyFrame(Duration.millis(100), e->casPohyb()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();


    }

    static int randrange(int a, int b) {
        return (int)((Math.random() * (b-a) +1 ) + a);
    }
    @FXML
    protected void chrobak() {
        int xp = randrange(110, 600);

        Chrobak ch = new Chrobak(xp, 600);
        root.getChildren().add(ch);
        ch.vykresli();

    }
    @FXML
    protected void mucha() {
        int xp = randrange(110, 600);
        int yp = randrange(120, 600);
        Mucha m = new Mucha(xp, yp);
        root.getChildren().add(m);
        m.vykresli();

    }

    @FXML protected void mys(MouseEvent evt) {
        int mx = (int)evt.getX();
        int my= (int)evt.getY();
        if (mx >= xpos && mx <= xpos + sizeW && my >= ypos && my <= ypos + sizeH) {
            ((Pane)getParent()).getChildren().remove(this);
        }
    }
}
