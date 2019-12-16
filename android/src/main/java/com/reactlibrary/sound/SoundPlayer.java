package com.reactlibrary.sound;

public interface SoundPlayer {
    void playNote(final int note, long duration);

    void noteOn(int note);

    void noteOff(int note);
}
