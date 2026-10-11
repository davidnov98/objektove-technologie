package com.example.robot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class robot extends Canvas {
    private GraphicsContext gc;
    private int canvasW;
    private int canvasH;
    private int robotX;
    private int robotY;
    private int robotRozmerX = 60;
    private int robotRozmerY = 40;
    private Timeline t;
    private boolean jeAutomat = false;
    private int smer = 0;
    public robot(int w, int h, int rx, int ry) {
        super(w, h);
        canvasH = h;
        canvasW = w;
        robotX = rx;
        robotY = ry;
        gc = getGraphicsContext2D();
        t = new Timeline(new KeyFrame(Duration.millis(100),e->automatAkcia()));
        t.setCycleCount(Timeline.INDEFINITE);
        kresli(robotX, robotY, KeyCode.A);
        setOnKeyPressed(evt -> pohyb(evt));
        setOnMouseClicked(evt -> mys(evt));
        setFocusTraversable(true);
        requestFocus();
    }
    public void mys(MouseEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        if ((x >= robotX && x <= robotX + robotRozmerX) &&
        y >= robotY && y <= robotY + robotRozmerY) {
            requestFocus();
            automat();
        }
    }
    public void automat() {
        smer = 0;
        if (!jeAutomat) {
            jeAutomat = true;
            t.play();
        }
        else {
            jeAutomat = false;
            t.stop();
        }
    }
    private void automatAkcia() {
        boolean ok = false;
        switch (smer) {
            case 0:
                ok = vlavo();
                break;
            case 1:
                ok = hore();
                break;
            case 2:
                ok = vpravo();
                break;
            case 3:
                ok = dole();
                break;
        }
        if (!ok) {
            smer = (smer + 1) % 4;
        }
    }
    private void kresli(int x, int y, KeyCode strana) {
        gc.clearRect(0,0, canvasW, canvasH);
        gc.setFill(Color.RED);
        gc.fillRect(x, y, robotRozmerX, robotRozmerY);
        oko(x, y, strana);

    }
    private void pohyb(KeyEvent e) {
        if (jeAutomat) {
            return;
        }
        KeyCode k = e.getCode();
        switch (k) {
            case W:
                hore();
                break;
            case S:
                dole();
                break;
            case A:
                vlavo();
                break;
            case D:
                vpravo();
                break;
            default:
                return;
        }
        kresli(robotX, robotY, k);
    }
    public boolean hore() {
        if (robotY - 5 >= 0) {
            robotY -= 5;
            kresli(robotX, robotY, KeyCode.W);
            return true;
        }
        return false;
    }
    public boolean dole() {
        if (robotY + 5 < canvasH - robotRozmerY) {
            robotY += 5;
            kresli(robotX, robotY, KeyCode.S);
            return true;
        }
        return false;
    }
    public boolean vlavo() {
        if (robotX - 5 >= 0) {
            robotX -= 5;
            kresli(robotX, robotY, KeyCode.A);
            return true;
        }
        return false;
    }
    public boolean vpravo() {
        if (robotX + 5 < canvasW - robotRozmerX) {
            robotX += 5;
            kresli(robotX, robotY, KeyCode.D);
            return true;
        }
        return false;
    }
    private void oko(int x, int y, KeyCode strana) {
        gc.setFill(Color.GREEN);
        switch (strana) {
            case W:
                y += 1;
                x += 17;
                break;
            case S:
                y += 19;
                x += 17;
                break;
            case A:
                y += 12;
                break;
            case D:
                y += 12;
                x += 40;
                break;
        }
        gc.fillOval(x, y, 20, 20);
    }
}
