package Screens;

import Characters.PronunciationAndTone;
import Utils.Utils;

import javax.swing.*;
import java.util.Map;

/** Screen that displays pronunciation -> character mapping and allows user to edit it. **/
public class PronunciationMappingEditor implements DictionaryScreen{

    /** Contain the mapping information. **/
    private PronunciationAndTone p;

    /** The main frame of the GUI. **/
    private JFrame mainFrame;

    /** Height of the main frame window. **/
    private final int windowHeight = 500;

    /** Width of the main frame window. **/
    private final int windowWidth = 500;

    /**
     * Instantiate a PronunciationEditorScreen either for Cantonese or Mandarin mappings.
     * @param p A PronunciationAndTone object to get the mapping information for the scree.
     */
    public PronunciationMappingEditor(PronunciationAndTone p) {
        this.p = p;
        this.mainFrame = new JFrame("Edit Pronunciation Mapping");
        this.mainFrame.setSize(this.windowWidth, this.windowHeight);
    }

    @Override
    public void drawToScreen() {
        JLabel labelPronunciation = new JLabel("Pronunciation");
        JLabel characterMap = new JLabel("Character");

        Utils.setJComponentBounds(labelPronunciation, 0.3, 0.5, 0.3, 0.3,
                this.windowWidth, this.windowHeight);




    }


}
