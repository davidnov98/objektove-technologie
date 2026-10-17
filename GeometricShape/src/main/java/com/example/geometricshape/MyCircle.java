package com.example.geometricshape;

import javafx.scene.paint.Color;

public class MyCircle extends GeometricShape {
    private double uhol = 0.1;
    public MyCircle(int x, int y, Color c, int w, int h) {
        super(x,y,c,w,h);
    }
    @Override
    public void vykresli() {
        super.gc.clearRect(0,0, canvasX, canvasY);
        super.gc.setFill(super.color);
        super.gc.fillOval(x,y,w,h);
    }
    @Override
    public void pohyb() {
        x += (int)(5* Math.cos(uhol));
        y += (int)(5 * Math.sin(uhol));
        uhol += 0.1;

    }
}
