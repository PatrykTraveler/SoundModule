import { NativeEventEmitter, NativeModules } from "react-native";

const { SoundModule } = NativeModules;

function init(touchKey, releaseKey, checkAccuracy) {
  SoundModule.connectMidiDevice();
  const eventEmitter = new NativeEventEmitter(SoundModule);
  eventEmitter.addListener("KeyEvent", event => {
    if (event.type == 1) {
      touchKey(event.note);
    }

    if (event.type == 0) {
      releaseKey(event.note);
    }
    checkAccuracy(event.note, event.type);
  });
}

export { init };
