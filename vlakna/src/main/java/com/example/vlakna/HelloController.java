package com.example.vlakna;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField tfNumber;
    @FXML
    private TextArea taFact;
    @FXML
    private TextArea taPow;
    @FXML
    private TextArea taFib;

    @FXML
    private ProgressBar pbFact;
    @FXML
    private ProgressBar pbPow;
    @FXML
    private ProgressBar pbFib;

    private long num = 0;

    private void vypis(TextArea ta, ProgressBar pb, String s, double progress) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ta.appendText(s + "\n");
                pb.setProgress(progress);
            }
        });
    }

    @FXML
    private void runAll() {
        boolean convOk = false;
        try {
            num = Long.parseLong(tfNumber.getText());
            convOk = true;
        }catch (Exception e) {
            System.out.println(e);
        }
        if (!convOk)
            return;

        taFact.clear();
        taFib.clear();
        taPow.clear();
        pbFact.setProgress(0.0);
        pbPow.setProgress(0.0);
        pbFib.setProgress(0.0);



        Runnable rFact = new Runnable() {
            @Override
            public void run() {
                factorial();
            }
        };
        Runnable rPow = new Runnable() {
            @Override
            public void run() {
                power();
            }

        };
        Runnable rFib = new Runnable() {
            @Override
            public void run() {
                fibonacci();
            }
        };
        Thread t1 = new Thread(rFact);
        Thread t2 = new Thread(rPow);
        Thread t3 = new Thread(rFib);
        t1.start();
        t2.start();
        t3.start();

    }

    private void factorial() {
        //5! 1*2*3*4*5
        long pom = 1;
        for (long i = 1; i <= num; ++i) {
            pom *= i;

            double percent = (double) i / num * 100;

            vypis(taFact, pbFact, "" + pom, percent);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }



    }
    private void power() {
        long pom = num;
        for (long i = 0; i < num; ++i) {
            pom *= num;

            double percent = (double) i / num * 100;
            vypis(taPow, pbPow, "" + pom, percent);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }
    private void fibonacci() {
        long x = 0;
        long y = 1;


        taFib.appendText("0\n");
        for (long i = 0; i < num; ++i) {
            long z = x + y;
            y = x;
            x = z;

            double percent = (double) i / num * 100;
            vypis(taFib, pbFib, "" + x, percent);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }
}
