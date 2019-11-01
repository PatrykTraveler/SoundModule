import { NativeEventEmitter, NativeModules } from "react-native";

const { SoundModule } = NativeModules;

export default function registerForKeyEvents(touchKey, releaseKey) {
  const eventEmitter = new NativeEventEmitter(SoundModule);
  eventEmitter.addListener("KeyEvent", event => {
    if (event.type == 1) {
      touchKey(event.note);
    }

    if (event.type == 0) {
      releaseKey(event.note);
    }
  });
}
