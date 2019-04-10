# gdx-boom
LibGDX extension that brings special effects to audio. 💥

**Warning! This library is still in early development!**

## Install

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the core dependency to your core module:

```groovy
dependencies {
    compile 'com.github.rafaskb.gdx-boom:core:master-SNAPSHOT'
}
```

**Step 3.** Add the lwjgl3 dependency to your desktop module:

```groovy
dependencies {
    compile 'com.github.rafaskb.gdx-boom:lwjgl3:master-SNAPSHOT'
}
```

## Usage

1. Initiate Boom in your game's class, ideally in the `create()` method.
2. Create channels and add any effects you want to them.
3. Create your sounds.
4. Play your sounds through Boom.

```java
public class MyGame extends Game {
    @Override
    public void create() {
        // Init Boom
        Boom boom = Boom.init();
        
        // Create a channel to route your sounds through
        int channelId = 1;
        BoomChannel myChannel = boom.createChannel(channelId);
        myChannel.addReverb(ReverbPreset.AUDITORIUM);
        
        // Create your sounds
        Sound mySound = Gdx.audio.newSound(Gdx.files.internal("path/to/sound.wav"));
        
        // Play your sounds through Boom
        boom.play(sound, myChannel);
    }
}
```
