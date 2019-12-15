package com.reactlibrary.midi;

public interface MidiDeviceService {
    void setUpDeviceListener();

    void startDriver();

    void stopDriver();
}
