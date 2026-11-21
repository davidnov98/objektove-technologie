package com.example.hmyz;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Hmyz extends Canvas {
    protected GraphicsContext gc;
    protected static final int canvasX = 700;
    protected static final int canvasY = 700;
    protected Color color;
    protected int xpos;
    protected int ypos;
    protected int sizeW;
    protected int sizeH;
    protected int rychlost = 5;
    public Hmyz(Color c, int xp, int yp, int w, int h) {
        super(canvasX,canvasY);
        gc = this.getGraphicsContext2D();
        color = c;
        xpos = xp;
        ypos = yp;
        sizeH = h;
        sizeW = w;
    }

    public void moveYpos(int y) {

        if (ypos - y > 0) {
            ypos -= y;
        }

    }
    public abstract void vykresli();
    public abstract void pohyb();

    public boolean pohybVlavo() {
        if (xpos - rychlost > 0) {
            xpos -= rychlost;
            vykresli();
            return true;
        }
        return false;
    }
    public boolean pohybVpravo() {
        if (xpos + rychlost < canvasX - sizeW) {
            xpos += rychlost;
            vykresli();
            return true;
        }
        return false;
    }
    public boolean pohybHore() {
        if (ypos - rychlost > 0) {
            ypos -= rychlost;
            vykresli();
            return true;
        }
        return false;
    }
    public boolean pohybDole() {
        if (ypos + rychlost < canvasY - sizeH) {
            ypos += rychlost;
            vykresli();
            return true;
        }
        return false;
    }

}
