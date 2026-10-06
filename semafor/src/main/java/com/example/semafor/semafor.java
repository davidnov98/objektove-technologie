package com.example.semafor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class semafor extends Canvas {
    private GraphicsContext gc;
    private int stav = 0;
    private Timeline t;
    private boolean bezi = false;
    private boolean jeZapnuty = true;

    public semafor() {
        super(150, 400);
        this.gc = this.getGraphicsContext2D();
        vykresli(Color.RED, Color.BLACK, Color.BLACK);
        setOnMousePressed(e->zmenStav());
        t = new Timeline(new KeyFrame(Duration.seconds(1), e->zmenStav()));
        t.setCycleCount(Timeline.INDEFINITE);
        
    }
    public void prepni() {
        if (bezi)
            stop();
        else
            start();
    }
    private void start() {
        t.play();
        bezi = true;
    }
    private void stop() {
        t.stop();
        bezi = false;
    }
    private void vykresli(Color red, Color yellow, Color green) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, 150, 400);
        gc.setFill(red);
        gc.fillOval(30,30,80,80);
        gc.setFill(yellow);
        gc.fillOval(30, 30+80+10, 80, 80);
        gc.setFill(green);
        gc.fillOval(30, 30+80+10+80+10, 80,80);
    }
    public void zmenStav() {

        if (stav == 3)
            stav = 0;
        else
            stav++;

        if (!jeZapnuty) {
            if (stav % 2 == 0)
                vykresli(Color.BLACK, Color.ORANGE, Color.BLACK);
            else
                vykresli(Color.BLACK, Color.BLACK, Color.BLACK);
            return;
        }


        switch (stav) {
            case 0:
                vykresli(Color.RED, Color.BLACK, Color.BLACK);
                break;
            case 1:
                vykresli(Color.RED, Color.ORANGE, Color.BLACK);
                break;
            case 2:
                vykresli(Color.BLACK, Color.BLACK, Color.GREEN);
                break;
            case 3:
                vykresli(Color.BLACK, Color.ORANGE, Color.BLACK);
                break;

        }
    }
    public void vypnut() {
        if (jeZapnuty) {
            stav = 0;
            jeZapnuty = false;
            start();
        }
    }
    public void zapnut() {
        if (!jeZapnuty) {
            stav = 0;
            jeZapnuty = true;
            start();
            vykresli(Color.RED, Color.BLACK, Color.BLACK);
        }
    }
}
