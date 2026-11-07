package com.example.firma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class HelloController {
    @FXML
    private CheckBox cbWarranty;
    @FXML
    private TextField tfName, tfType,tfSn,tfPrice;
    @FXML
    private ListView<Hardware> lvItems;
    @FXML
    private DatePicker dPicker;

    private ObservableList<Hardware> ol = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        lvItems.setItems(ol);
    }
    @FXML
    protected void liMouse() {
        Hardware h = lvItems.getSelectionModel().getSelectedItem();
        tfName.setText(h.getName());
        tfType.setText(h.getType());
        tfPrice.setText("" +h.getBuyPrice());
        tfSn.setText(h.getSn());
        dPicker.setValue(h.getWarranty());


    }
    @FXML
    protected void cbMouse() {
       boolean b = cbWarranty.isSelected();
       for (int i = 0; i < ol.size(); ++i) {
           Hardware h = ol.get(i);
           Hardware.setPrintState(b);
           ol.set(i, h);
       }
    }
    @FXML
    protected void add() {
        String name = tfName.getText();
        String type = tfType.getText();
        String sn = tfSn.getText();
        double buyPrice = Double.parseDouble(tfPrice.getText());
        LocalDate endW = dPicker.getValue();

        Hardware h = new Hardware(name, type, sn, buyPrice, endW);
        ol.add(h);

    }
    @FXML
    protected void remove() {
        ol.remove(lvItems.getSelectionModel().getSelectedItem());
    }
    @FXML
    protected void update() {

        int idx = lvItems.getSelectionModel().getSelectedIndex();
        if (idx == -1)
            return;
        String name = tfName.getText();
        String type = tfType.getText();
        String sn = tfSn.getText();
        double buyPrice = Double.parseDouble(tfPrice.getText());
        LocalDate endW = dPicker.getValue();

        Hardware h = new Hardware(name, type, sn, buyPrice, endW);

        ol.set(idx, h);
    }
}
