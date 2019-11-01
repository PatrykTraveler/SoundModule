package com.reactlibrary;

import com.facebook.react.bridge.Callback;

import java.util.Random;

public class Simulator {
    Callback touchKey;
    Callback releaseKey;
    int keyRange = 12;
    Thread simulationThread;

    public Simulator(Callback touchKey, Callback releaseKey) {
        this.touchKey = touchKey;
        this.releaseKey = releaseKey;
    }

    public void runSimulation() {
        final Random random = new Random();
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int note = random.nextInt() % keyRange + 60;
                    touchKey.invoke(note);
                    try {
                        Thread.sleep(100 * (random.nextInt() % 5 + 1));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    releaseKey.invoke(note);
                }
            }
        };
        simulationThread = new Thread(myRunnable);
        simulationThread.run();
    }

    public void stopSimulation() {
        simulationThread.interrupt();
    }
}
