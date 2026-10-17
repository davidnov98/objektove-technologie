package com.example.geometricshape;

import javafx.scene.paint.Color;

public class MySquare extends MyRectangle{
    public MySquare(int x, int y, Color c, int w) {
        super(x,y,c,w,w);
    }

    @Override
    protected boolean pohyb1() {
        if (y - 5 >= 0) {
            y -= 5;
            vykresli();
            return true;
        }
        return false;
    }
    @Override
    protected boolean pohyb2() {
        if (y + 5 < canvasY - h) {
            y += 5;
            vykresli();
            return true;
        }
        return false;
    }

}
