package com.reactlibrary.midi;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;

import com.facebook.react.bridge.ReactApplicationContext;

public class MidiDeviceServiceImpl implements MidiDeviceService {
    private final MidiHandler midiHandler;
    private final MidiManager midiManager;

    public MidiDeviceServiceImpl(ReactApplicationContext reactContext) {
        midiHandler = new MidiHandler(reactContext);
        midiManager = (MidiManager) reactContext.getSystemService(ReactApplicationContext.MIDI_SERVICE);
    }

    @Override
    public void setUpDeviceListener() {
        final MidiManager.OnDeviceOpenedListener onDeviceOpenedListener = new MidiManager.OnDeviceOpenedListener() {
            @Override
            public void onDeviceOpened(MidiDevice device) {
                if (device != null) {
                    MidiOutputPort outputPort = device.openOutputPort(0);
                    outputPort.connect(midiHandler);
                }
            }
        };

        midiManager.registerDeviceCallback(new MidiManager.DeviceCallback() {
            @Override
            public void onDeviceAdded(MidiDeviceInfo info) {
                midiManager.openDevice(info, onDeviceOpenedListener, null);
            }
        }, null);

        startDriver();
    }

    @Override
    public void startDriver() {
        midiHandler.startDriver();
    }

    @Override
    public void stopDriver() {
        midiHandler.stopDriver();
    }
}
