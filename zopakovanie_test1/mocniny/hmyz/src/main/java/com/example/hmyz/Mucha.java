package com.example.hmyz;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Mucha extends Hmyz {
    private int status = 0;
    private boolean stopped = false;
    public Mucha(int xpos, int ypos) {
        super(Color.BLUE, xpos,ypos, 35, 35);
        setOnMouseClicked(evt -> mys(evt));
        setOnKeyPressed(evt -> klavesnica(evt));
        setFocusTraversable(true);
        requestFocus();
    }
    public void klavesnica(KeyEvent evt) {
        KeyCode k = evt.getCode();
        if (k == KeyCode.S) {
            stopped = !stopped;
        }

    }
    /*
    public void mys(MouseEvent evt) {
        int mx = (int)evt.getX();
        int my= (int)evt.getY();
        if (mx >= xpos && mx <= xpos + sizeW && my >= ypos && my <= ypos + sizeH) {
            ((Pane)getParent()).getChildren().remove(this);
        }
    }
    */
     */
    @Override
    public void vykresli() {
        gc.clearRect(0,0, canvasX, canvasY);
        gc.setFill(super.color);
        gc.fillOval(xpos, ypos, sizeW, sizeH);

    }
    public void pohyb() {
        if (stopped)
            return;
        switch (status) {
            case 0:
                pohybVpravo();
                break;
            case 1:
                pohybVlavo();
                break;
            case 2:
                pohybHore();
                break;
            case 3:
                pohybDole();
                break;

        }
        status = (int)((Math.random() * (3-0+1) ) + 0);



    }
}
