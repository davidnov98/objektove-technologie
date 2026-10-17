package com.example.animal;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Animal extends Canvas {
    protected GraphicsContext gc;
    protected final static int canvasW = 700;
    protected final static int canvasH = 540;
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected int moveDirection = 0;
    protected Color color;
    protected int moveRangeX = canvasW;
    protected int moveRangeY = canvasH;
    public Animal(int nx, int ny, Color c, int nw, int nh) {
        super(canvasW,canvasH);
        gc = this.getGraphicsContext2D();
        x = nx;
        y = ny;
        w = nw;
        h = nh;
        color = c;
    }
    protected boolean vpravo() {
        if (x + 5 <= moveRangeX - w) {
            x+=5;
            kresli();
            return true;
        }
        return false;
    }
    public void dalsiPohyb() {
        moveDirection = (moveDirection + 1) % 4;
    }
    protected boolean vlavo() {
        if (x - 5 >= 0) {
            x-=5;
            kresli();
            return true;
        }
        return false;
    }

    protected boolean hore() {
        if (y - 5 >= 0) {
            y-=5;
            kresli();
            return true;
        }
        return false;
    }

    protected boolean dole() {
        if (y + 5 <= moveRangeY - h) {
            y += 5;
            kresli();
            return true;
        }
        return false;
    }


    public abstract void kresli();
    public abstract void pohyb();
}
