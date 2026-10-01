package com.example.prevodjednotiek;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
   @FXML
   private ChoiceBox<String> cb1;
   @FXML
   private TextField tf1;
   private String[] moznosti = {"mg","g", "kg", "t"};
                            //   0    1    2     3

   @FXML
   private Label lblMg, lblG, lblKg, lblT;

    @FXML
    protected void initialize() {
        cb1.setItems(FXCollections.observableArrayList(moznosti));
        cb1.setValue(moznosti[0]);

    }
    int index(String moznost) {
        for (int i = 0; i < moznosti.length; ++i) {
            if (moznosti[i].equals(moznost))
                return i;
        }
        return -1;
    }
    private double prevedNa(double hodnota, String jednotka, String jednotkaNa) {

        int idPovodny = index(jednotka);
        int idPrevedeny = index(jednotkaNa);
        if (idPovodny == idPrevedeny) {
            return hodnota;
        }else if (idPovodny > idPrevedeny) {
            for (int i = idPovodny; i > idPrevedeny; --i) {
                hodnota *= 1000.0;
            }
        }
        else {
            for (int i = idPovodny; i < idPrevedeny; ++i) {
                hodnota /= 1000.0;
            }
        }
        return hodnota;
    }
    @FXML
    protected void prevod() {
        double hodnota = 0.0;
        boolean ok = false;
        try {
                hodnota = Double.parseDouble(tf1.getText());
                ok = true;
        }catch (Exception e) {
            System.out.println((e.getMessage()));
        }
        if (!ok)
            return;
        String prevodZ = cb1.getValue();


        lblMg.setText("mg: " + prevedNa(hodnota, prevodZ, "mg"));
        lblG.setText("g: " + prevedNa(hodnota, prevodZ, "g"));
        lblKg.setText("kg: " + prevedNa(hodnota, prevodZ, "kg"));
        lblT.setText("t: " + prevedNa(hodnota, prevodZ, "t"));




    }
}
