package com.example.auto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class auto extends Canvas {
    private final GraphicsContext gc;
    private final int width;
    private final int height;
    private final int carW;
    private final int carH;
    private int carX = 40;
    private int carY = 20;
    private boolean jeAutomat = false;
    boolean automatVpravo= true;

    private final Timeline t;
    public auto(int w, int h, int cW, int cH) {
        super(w,h);
        this.gc = this.getGraphicsContext2D();
        width = w;
        height = h;
        carW = cW;
        carH = cH;
        nakresli(carX,carY);

        t = new Timeline(new KeyFrame(Duration.millis(100), e->automatAkcia()));
        t.setCycleCount(Timeline.INDEFINITE);
        setOnMouseClicked(evt -> mys(evt));
        setOnKeyPressed(evt -> pohyb(evt));
        setFocusTraversable(true);
        requestFocus();


    }
    public void mys(MouseEvent m) {
        requestFocus();
        automat();

    }
    public void automat() {
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

        if (automatVpravo) {
            if (!doprava())
                automatVpravo = false;
        }
        else {
            if (!dolava())
                automatVpravo = true;
        }

    }

    private void pohyb(KeyEvent e) {
        if (jeAutomat)
            return;
        KeyCode k = e.getCode();
        if (k == KeyCode.RIGHT)
            doprava();
        else if (k == KeyCode.LEFT)
            dolava();
        else if (k == KeyCode.UP)
            hore();
        else if (k == KeyCode.DOWN)
            dole();

    }
    public boolean doprava() {
        if (carX + 10 <= width - carW) {
            carX += 10;
            nakresli(carX, carY);
            return true;
        }
        return false;

    }
    public boolean dolava() {
        if (carX - 10 >= 0) {
            carX -= 10;
            nakresli(carX, carY);
            return true;
        }
        return false;
    }
    public boolean hore() {
        if (carY - 10 >= 0) {
            carY -= 10;
            nakresli(carX, carY);
            return true;
        }
        return false;
    }
    public boolean dole() {
        if (carY + 10 <= height - carH) {
            carY += 10;
            nakresli(carX, carY);
            return true;
        }
        return false;
    }
    public void vymaz() {
        gc.clearRect(0, 0, width,height);
    }
    private void nakresli(int x, int y) {
        vymaz();
        gc.setFill(Color.RED);
        gc.fillRect(x,y, carW, carH);

    }
}
