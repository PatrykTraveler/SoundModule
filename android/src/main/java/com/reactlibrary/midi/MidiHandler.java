package com.reactlibrary.midi;

import android.media.midi.MidiReceiver;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.billthefarmer.mididriver.MidiDriver;

public class MidiHandler extends MidiReceiver {
    private ReactApplicationContext reactApplicationContext;
    private final MidiDriver midiDriver = new MidiDriver();

    public MidiHandler(ReactApplicationContext reactApplicationContext) {
        this.reactApplicationContext = reactApplicationContext;
    }

    public void startDriver() {
        midiDriver.start();
    }

    public void sendEvent(String eventName, WritableMap params) {
        this.reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }


    @Override
    public void onSend(byte[] msg, int offset, int count, long timestamp) {
        midiDriver.write(msg);

        WritableMap params = Arguments.createMap();

        params.putInt("type", msg[1]);
        params.putInt("note", msg[2]);
        sendEvent("KeyEvent", params);
    }
}
