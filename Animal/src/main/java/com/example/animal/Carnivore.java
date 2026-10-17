package com.example.animal;

import javafx.scene.paint.Color;

public class Carnivore extends Animal{
    public Carnivore() {
        super(0, 0, Color.RED, 50,50);

    }

    @Override
    public void kresli() {
        gc.clearRect(0,0,canvasW, canvasH);
        gc.setFill(color);
        gc.fillRect(x,y,w,h);
    }

    @Override
    public void pohyb() {
        boolean ok = false;
        switch (moveDirection) {
            case 0:
                ok = hore();
                break;
            case 1:
                ok = vlavo();
                break;
            case 2:
                ok = dole();
                break;
            case 3:
                ok = vpravo();
                break;
        }
        if (!ok)
            moveDirection = (moveDirection + 1) % 4;
    }
}
