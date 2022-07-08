package Utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer {

    /**
     * Play the audio file specified by the file path.
     * @param filePath  String, path to the audio file.
     */
    public static void play(String filePath) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
