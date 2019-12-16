import { NativeEventEmitter, NativeModules } from "react-native";

const { SoundModule } = NativeModules;
const NOTE_ON = -112;
const NOTE_OFF = -128;

function setUpMidi(touchKey, releaseKey) {
  SoundModule.connectMidiDevice();
  const eventEmitter = new NativeEventEmitter(SoundModule);
  eventEmitter.addListener("KeyEvent", event => {
    if (event.type == -112) {
      touchKey(event.note);
    }

    if (event.type == -128) {
      releaseKey(event.note);
    }
  });
}

function playNote(note, duration) {
  if (duration !== undefined) {
    SoundModule.playNoteWithDuration(note, duration);
  } else {
    SoundModule.playNote(note);
  }
}

function stopNote(note) {
  SoundModule.stopNote(note);
}

export { setUpMidi, playNote, stopNote };
