# react-native-sound-module

## Getting started

`$ npm install react-native-sound-module --save`

### Mostly automatic installation

`$ react-native link react-native-sound-module`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-sound-module` and add `SoundModule.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libSoundModule.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.SoundModulePackage;` to the imports at the top of the file
  - Add `new SoundModulePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-sound-module'
  	project(':react-native-sound-module').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sound-module/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sound-module')
  	```


## Usage
```javascript
import SoundModule from 'react-native-sound-module';

// TODO: What to do with the module?
SoundModule;
```
