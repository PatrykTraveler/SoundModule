import { NativeEventEmitter, NativeModules } from "react-native";

const { SoundModule } = NativeModules;

function registerForKeyEvents(touchKey, releaseKey, ref) {
  const eventEmitter = new NativeEventEmitter(SoundModule);
  eventEmitter.addListener("KeyEvent", event => {
    if (event.type == 1) {
      touchKey(event.note, ref);
    }

    if (event.type == 0) {
      releaseKey(event.note, ref);
    }
  });
}

function startSimulation() {
  SoundModule.startSimulation();
}

function stopSimulation() {
  SoundModule.stopSimulation();
}

export { startSimulation, stopSimulation, registerForKeyEvents };
