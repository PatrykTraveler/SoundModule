package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class SoundModuleModule extends ReactContextBaseJavaModule {
    private Thread thread;
    private final ReactApplicationContext reactContext;

    public SoundModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SoundModule";
    }

    @ReactMethod
    public void startSimulation() {
        Simulator simulator = new Simulator(reactContext);
        Thread thread = new Thread(simulator);
    }

    @ReactMethod
    public void stopSimulation(){
        thread.interrupt();
    }
}
