package com.reactlibrary.sound;

import org.billthefarmer.mididriver.MidiDriver;

public class MidiSoundPlayer implements SoundPlayer {
    private int NOTE_ON = 0x90;
    private int NOTE_OFF = 0x80;
    private int MAX_VELOCITY = 127;
    private int MIN_VELOCITY = 0;
    private final MidiDriver midiDriver;

    public MidiSoundPlayer() {
        midiDriver = new MidiDriver();
        midiDriver.start();
    }

    @Override
    public void noteOn(int note) {
        sendMidi(NOTE_ON, note, MAX_VELOCITY);
    }

    @Override
    public void noteOff(int note) {
        sendMidi(NOTE_OFF, note, MIN_VELOCITY);
    }

    public void sendMidi(int event, int note, int velocity) {
        byte[] msg = new byte[3];

        msg[0] = (byte) event;
        msg[1] = (byte) note;
        msg[2] = (byte) velocity;

        midiDriver.write(msg);
    }
}
