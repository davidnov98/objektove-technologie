package com.example.banka;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private Label lBalance;
    @FXML private ComboBox<String> cbType;
    @FXML private TextField tfSum;
    @FXML private TextField tfStart;
    @FXML private TextArea taTransactions;
    private BankAccount ba;
    @FXML
    private void initialize() {
        cbType.getItems().addAll("withdraw", "deposit");
        cbType.setValue("withdraw");
    }

    @FXML
    private void runOne() {
        if (ba == null)
            return;
        double sum = getInput(tfSum);
        if (sum == -1.0)
            return;
        run(new Employee(ba), cbType.getValue(), sum);
    }


    @FXML
    private void runFive() {
        if (ba == null)
            return;
        for (int i = 0; i < 5; ++i) {
            double x  = Math.random();
            String t = "withdraw";
            if (x < 0.5)
                t = "deposit";

            double sum = (int)(Math.random() * 100) + 1;
            run(new Employee(ba), t, sum);
        }
    }

    private void run(Employee e, String type, double sum) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String m = e.transaction(type, sum);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        taTransactions.appendText(m + "\n");
                        lBalance.setText("" + ba.getAmount() + "€");
                    }
                });
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.start();
    }

    private double getInput(TextField tf) {
        boolean ok = false;
        double v = -1.0;
        try {
            v = Double.parseDouble(tf.getText());
            ok = true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!ok || v < 0.0)
            return -1.0;
        else
            return v;


    }

    @FXML
    private void createAccount() {
        double sum = getInput(tfStart);
        if (sum == -1)
            return;

        ba = new BankAccount(sum);
        taTransactions.clear();
        lBalance.setText("" + sum + "€");

    }

}
