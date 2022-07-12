package Utils;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

    /**
     * Play the audio file specified by the file path.
     * @param filePath  String, path to the audio file.
     */
    public static void play(String filePath) {

        try {
            JFXPanel j = new JFXPanel();

            Media hit = new Media(new File(filePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        } catch (MediaException e) {
            System.out.println("Audio file not found");
        }

    }
}
