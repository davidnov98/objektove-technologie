package com.example.geometricshape;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GeometricShape extends Canvas {
    protected static final int canvasX = 620;
    protected static final int canvasY = 600;
    protected GraphicsContext gc;
    protected Color color;
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    public GeometricShape(int x, int y, Color c, int w, int h) {
        super(canvasX, canvasY);
        this.x = x;
        this.y = y;
        color = c;
        gc = this.getGraphicsContext2D();
        this.w = w;
        this.h = h;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int nx) {
        if (nx >= 0 && nx <= canvasX - w)
            x = nx;
    }
    public void setY(int ny) {
        if (ny >= 0 && ny <= canvasY - h)
            y = ny;
    }
    public void setColor(Color c) {
        this.color = c;
    }
    public abstract void vykresli();
    public abstract void pohyb();
}
