package com.example.animal;

import javafx.scene.paint.Color;

public class Herbivore extends Animal{
    public Herbivore() {
        super((int) (Math.random() * 250), (int) (Math.random() * 250), Color.GREEN, 35,35);
        moveRangeX = (int)(Math.random() * (canvasW - 250 + 1) + 250);
        moveRangeY = (int)(Math.random() * (canvasH - 250 + 1) + 250);

    }
    @Override
    public void kresli() {
        gc.clearRect(0, 0, canvasW, canvasH);
        gc.setFill(color);
        gc.fillOval(x, y, w, h);
    }
    @Override
    public void pohyb() {
        boolean ok = false;
        switch (moveDirection) {
            case 0:
                ok = vpravo();
                break;
            case 1:
                ok = dole();
                break;
            case 2:
                ok = vlavo();
                break;
            case 3:
                ok = hore();
                break;
        }
        if (!ok)
            moveDirection = (moveDirection + 1) % 4;
    }
}
