package uet.oop.bomberman.sound;

import uet.oop.bomberman.Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sound {
    private File file;
    private AudioInputStream stream;
    private AudioFormat format;
    private Clip clip;

    private final Map<String, String> listFile;
    private final String[] files = {"Background", "PlaceBomb", "Explosion", "EnemyDead", "Defeated", "Victory", "PowerUp"};

    public Sound() {
        listFile = new HashMap<>();
        for (String file : files) {
            listFile.put(file, "res/sound/" + file + ".wav");
        }
    }

    /**
     * @param filename the name of the file that is going to be played
     */
    private void playSound(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        file = new File(filename);
        stream = AudioSystem.getAudioInputStream(file);
        format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
    }

    public void getBgSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("Background"));
        loop();
    }

    public void getPutBomSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("PlaceBomb"));
    }

    public void getExplosionSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("Explosion"));
        Game.isExplosion = false;
    }

    public void getEnemyDeadSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("EnemyDead"));
        Game.isEnemyDead = false;
    }

    public void getPlayerDeadSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("Defeated"));
        Game.isPlayerDead = false;
    }

    public void getNextLevelSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("Victory"));
        Game.isNextLv = false;
    }

    public void getItemSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(listFile.get("PowerUp"));
        Game.isGetItem = false;
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

