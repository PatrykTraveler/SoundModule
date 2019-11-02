package com.reactlibrary;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulator implements Runnable {
    int keyRange = 12;
    Thread simulationThread;
    final Random random = new Random();
    AtomicBoolean running = new AtomicBoolean(false);

    ReactApplicationContext reactApplicationContext;

    public Simulator(ReactApplicationContext reactApplicationContext) {
        this.reactApplicationContext = reactApplicationContext;
    }

    public void sendEvent(String eventName, WritableMap params) {
        this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private void touchKey(int note) {
        WritableMap params = Arguments.createMap();
        params.putInt("type", 1);
        params.putInt("note", note);
        sendEvent("KeyEvent", params);
    }

    private void releaseKey(int note) {
        WritableMap params = Arguments.createMap();
        params.putInt("type", 0);
        params.putInt("note", note);
        sendEvent("KeyEvent", params);
    }

    public void stopSimulation() {
        simulationThread.interrupt();
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            int note = random.nextInt(keyRange) + 60;
            touchKey(note);
            try {
                Thread.sleep(100 * (random.nextInt(5) + 1));
            } catch (InterruptedException e) {
                running.set(false);
            }
            releaseKey(note);
        }
    }
}
