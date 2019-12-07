import { NativeEventEmitter, NativeModules } from "react-native";

const { SoundModule } = NativeModules;

function init(touchKey, releaseKey) {
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

export { init };
