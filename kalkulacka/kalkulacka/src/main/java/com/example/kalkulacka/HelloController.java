package com.example.kalkulacka;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {
    @FXML private TextField tf1, tf2;
    @FXML private  Label lbl1;

    @FXML protected  void add() {
        int a = Integer.parseInt(tf1.getText());
        int b = Integer.parseInt(tf2.getText());
        lbl1.setText("Result: " + String.valueOf(a+b));
    };
    @FXML protected  void sub() {
        int a = Integer.parseInt(tf1.getText());
        int b = Integer.parseInt(tf2.getText());
        lbl1.setText("Result: " + String.valueOf(a-b));
    };
    @FXML protected  void div() {
        int a = Integer.parseInt(tf1.getText());
        int b = Integer.parseInt(tf2.getText());
        if (b == 0) {
            lbl1.setText(("Error"));
            return;
        }
        lbl1.setText("Result: " + String.valueOf(a/b));
    };
    @FXML protected  void mul() {
        int a = Integer.parseInt(tf1.getText());
        int b = Integer.parseInt(tf2.getText());
        lbl1.setText("Result: " + String.valueOf(a*b));
    };
}
