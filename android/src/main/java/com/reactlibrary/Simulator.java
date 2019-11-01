package com.reactlibrary;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Random;

public class Simulator {
    int keyRange = 12;
    Thread simulationThread;
    ReactApplicationContext reactApplicationContext;

    public Simulator(ReactApplicationContext reactApplicationContext) {
        this.reactApplicationContext = reactApplicationContext;
    }

    public void sendEvent(String eventName, WritableMap params) {
        this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private void touchKey(int note){
        WritableMap params = Arguments.createMap();
        params.putInt("type", 1);
        params.putInt("note", note);
        sendEvent("KeyEvent", params);
    }

    private void releaseKey(int note){
        WritableMap params = Arguments.createMap();
        params.putInt("type", 0);
        params.putInt("note", note);
        sendEvent("KeyEvent", params);
    }

    public void runSimulation() {
        final Random random = new Random();
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int note = random.nextInt() % keyRange + 60;
                    touchKey(note);
                    try {
                        Thread.sleep(100 * (random.nextInt() % 5 + 1));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    releaseKey(note);
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
