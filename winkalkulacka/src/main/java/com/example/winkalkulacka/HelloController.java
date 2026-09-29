package com.example.winkalkulacka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private TextField tfHistory, tfDisp;

    private String znamienko = "";
    private int vysledok = 0;
    private boolean zadane = false;

    @FXML
    protected void btnCifra(ActionEvent e) {
        String cislo = ((Button) e.getSource()).getText();
        if (!zadane) {
            tfDisp.setText(cislo);
        } else {
            tfDisp.setText(tfDisp.getText() + cislo);
        }
        zadane = true;
    }

    @FXML
    protected void btnZnam(ActionEvent e) {
        if (tfDisp.getText().equals("Error")) return;

        String znam = ((Button) e.getSource()).getText();
        int c = Integer.parseInt(tfDisp.getText());

        if (znamienko.isEmpty()) {
            vysledok = c;
        } else if (zadane) {
            switch (znamienko) {
                case "+": vysledok += c; break;
                case "-": vysledok -= c; break;
                case "*": vysledok *= c; break;
                case "/":
                    if (c == 0) {
                        clr();
                        tfDisp.setText("Error");
                        return;
                    }
                    vysledok /= c;
                    break;

            }
        }

        znamienko = znam;
        zadane = false;
        tfHistory.setText(vysledok + " " + znam);
        tfDisp.setText("" + vysledok);
    }

    @FXML
    protected void clr() {
        vysledok = 0;
        znamienko = "";
        zadane = false;
        tfHistory.clear();
        tfDisp.setText("0");
    }


}