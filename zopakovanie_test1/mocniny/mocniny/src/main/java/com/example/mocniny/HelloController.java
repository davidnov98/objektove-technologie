package com.example.mocniny;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class HelloController {
    private volatile boolean statusRun = false;
    private boolean threadsInit = false;
    private Thread t2, t3;

    @FXML
    private Spinner<Integer> sp;

    @FXML
    private TextArea ta1,ta2;

    private void clear() {
        ta1.clear();
        ta2.clear();
        threadsInit = false;
    }

    @FXML
    private void initialize() {
        sp.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));
        sp.getValueFactory().setValue(1);
    }
    private void mocn(TextArea t, int n, int p, int wait) {

        for (int i = 1; i <= n; ++i) {
            while (!statusRun) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            long moc = (long) Math.pow(i, p);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    t.appendText(moc + "\n");
                }
            });
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void stop() {
        if (!threadsInit || t2 == null || t3 == null)
            return;
        statusRun = !statusRun;

    }
    @FXML
    protected void threadinit() {
        clear();
        int num = sp.getValue();
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                mocn(ta1, num, 2, 200);
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                mocn(ta2, num, 3, 300);
            }
        };
        t2 = new Thread(r2);
        t3 = new Thread(r3);
        t2.setDaemon(true);
        t3.setDaemon(true);
        threadsInit = true;

        t2.start();
        t3.start();

    }
}
