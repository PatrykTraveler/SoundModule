package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reactlibrary.midi.MidiDeviceService;
import com.reactlibrary.midi.MidiDeviceServiceImpl;
import com.reactlibrary.sound.MidiSoundPlayer;
import com.reactlibrary.sound.SoundPlayer;

public class SoundModuleModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private final MidiDeviceService midiDeviceService;
    private final SoundPlayer soundPlayer;

    public SoundModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        midiDeviceService = new MidiDeviceServiceImpl(reactContext);
        soundPlayer = new MidiSoundPlayer();
    }

    @Override
    public String getName() {
        return "SoundModule";
    }

    @ReactMethod
    public void connectMidiDevice() {
        midiDeviceService.setUpDeviceListener();
    }

    @ReactMethod
    public void playNote(int note) {
        soundPlayer.noteOn(note);
    }

    @ReactMethod
    public void stopNote(int note) {
        soundPlayer.noteOff(note);
    }
}
