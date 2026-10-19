package com.example.zoznamucasnikov;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    ListView<String> list;
    @FXML
    TextField name;
    private ObservableList<String> zoznam = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        list.setItems(zoznam);
    }
    @FXML
    private void add() {
        String text = name.getText();
        if (text.isEmpty()) {
            return;
        }
        zoznam.add(text);
    };
    @FXML
    private void remove() {
        if (list.getSelectionModel().getSelectedIndex() != -1) {
            zoznam.remove(list.getSelectionModel().getSelectedIndex());

        }
    };
    @FXML
    private void update() {
        int index = list.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            if (!name.getText().isEmpty()) {
                String text = name.getText();
                zoznam.set(index, text);

            }
        }
    }

}
