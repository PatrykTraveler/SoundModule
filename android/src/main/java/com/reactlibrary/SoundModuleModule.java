package com.reactlibrary;

import android.content.Context;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reactlibrary.midi.MidiHandler;

public class SoundModuleModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private MidiHandler midiHandler;

    public SoundModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        midiHandler = new MidiHandler(reactContext); // USB midi device handler
        midiHandler.startDriver(); // This is starting the driver which plays the sound
    }

    @Override
    public String getName() {
        return "SoundModule";
    }

    @ReactMethod
    public void connectMidiDevice(){
        final MidiManager midiManager = (MidiManager)this.reactContext.getSystemService(ReactApplicationContext.MIDI_SERVICE);

        midiManager.registerDeviceCallback(new MidiManager.DeviceCallback() {
            @Override
            public void onDeviceAdded( MidiDeviceInfo info ) {
                midiManager.openDevice(info,onDeviceOpenedListener, null);
            }
        }, null);
    }

    private MidiManager.OnDeviceOpenedListener onDeviceOpenedListener = new MidiManager.OnDeviceOpenedListener() {
        @Override
        public void onDeviceOpened(MidiDevice device) {
            if (device != null) {
                MidiOutputPort outputPort = device.openOutputPort(0);
                outputPort.connect(midiHandler);
            }
        }
    };
}
