package com.example.hmyz;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Chrobak extends Hmyz{
    private int status = 0;
    public Chrobak(int xpos, int ypos) {
        super(Color.RED, xpos,ypos, 30, 30);

    }

    @Override
    public void vykresli() {
        gc.clearRect(0,0, canvasX, canvasY);
        gc.setFill(super.color);
        gc.fillRect(xpos, ypos, sizeW, sizeH);
    }
    @Override
    public void pohyb() {
        boolean ok = false;
        switch (status) {
            case 0:
                ok = pohybVpravo();
                break;
            case 1:
                ok = pohybVlavo();
                break;

        }
        if (!ok)
            status = (status + 1) % 2;


    }
}
