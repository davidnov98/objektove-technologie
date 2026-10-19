package com.example.nakupnyzozanm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label lPocet;
    @FXML
    private TextField txtPolozka, txtKusy;

    @FXML
    private ListView<Polozka> list;

    private ObservableList<Polozka> zoznam = FXCollections.observableArrayList();

    private void updateLabel() {
        lPocet.setText("Pocet: " + zoznam.size());
    }
    @FXML
    private void initialize() {
        list.setItems(zoznam);
        lPocet.setText("Pocet: " + zoznam.size());
    }
    private int getPocetPoloziek() {
        int polozka;
        try {
            polozka = Integer.parseInt(txtKusy.getText());
            if (polozka >= 0) return polozka;
            else return -1;
        }catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
    @FXML
    private void pridat() {
        String nazov = txtPolozka.getText();
        int pocet = getPocetPoloziek();
        if (pocet == -1 || nazov.isEmpty())
            return;


        zoznam.add(new Polozka(nazov, pocet));
        updateLabel();

        list.getSelectionModel().clearSelection();
    }
    @FXML
    private void upravit() {
        int i = list.getSelectionModel().getSelectedIndex();
        String nazov = txtPolozka.getText();
        int pocet = getPocetPoloziek();
        if (i == -1 || nazov.isEmpty() || pocet == -1) {
            return;
        }
        zoznam.set(i, new Polozka(nazov, pocet));
    }
    @FXML
    private void odstranit() {
        Polozka p = list.getSelectionModel().getSelectedItem();
        if (p == null)
            return;
        zoznam.remove(p);
        updateLabel();
        list.getSelectionModel().clearSelection();
    }
    @FXML
    private void mys() {
        Polozka p = list.getSelectionModel().getSelectedItem();
        if (p == null)
            return;
        txtKusy.setText("" + p.getPocet());
        txtPolozka.setText(p.getNazov());
    }

}
