package com.example.geometricshape;

import javafx.scene.paint.Color;

import java.security.ProtectionDomain;

public class MyRectangle extends GeometricShape {
    protected int smer = 0;
    public MyRectangle(int x, int y, Color c, int w, int h) {
        super(x,y,c,w,h);

    }

    @Override
    public void vykresli() {
        super.gc.clearRect(0,0, canvasX, canvasY);
        super.gc.setFill(super.color);
        super.gc.fillRect(x,y,w,h);
    }
    protected boolean pohyb1() {
        if (x - 5 >= 0) {
            x -= 5;
            vykresli();
            return true;
        }
        return false;
    }
    protected boolean pohyb2() {
        if (x + 5 <= canvasX - w) {
            x += 5;
            vykresli();
            return true;
        }
        return false;
    }

    @Override
    public void pohyb() {
        boolean ok = false;
        switch (smer) {
            case 0:
                ok = pohyb1();
                break;
            case 1:
                ok = pohyb2();
                break;
        }
        if (!ok)
            smer = (smer + 1) % 2;
    }
}
