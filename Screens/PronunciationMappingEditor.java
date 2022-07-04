package Screens;

import ButtonListeners.PronunciationMappingEditListener;
import Characters.PronunciationAndTone;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /** The error label. **/
    private JLabel errorLabel;

    /** The box to enter the pronunciation into. **/
    private JTextArea pronunciationBox;

    /** The box to enter the character the pronunciation will be mapped to. **/
    private JTextArea charBox;


    /**
     * Instantiate a PronunciationEditorScreen either for Cantonese or Mandarin mappings.
     * @param p A PronunciationAndTone object to get the mapping information for the scree.
     */
    public PronunciationMappingEditor(PronunciationAndTone p) {
        this.p = p;
        this.mainFrame = new JFrame("Edit Pronunciation Mapping");
        this.mainFrame.setSize(this.windowWidth, this.windowHeight);
        this.errorLabel = new JLabel();
        this.pronunciationBox =new JTextArea();
        this.pronunciationBox.setName("Pronunciation");
        this.charBox = new JTextArea();
        this.charBox.setName("Character");
    }

    @Override
    public void drawToScreen() {
        JLabel labelPronunciation = new JLabel("Pronunciation");

        JLabel characterMap = new JLabel("Character");
        JButton editButton = new JButton("Edit");
        editButton.setName("Edit");
        editButton.addActionListener(new PronunciationMappingEditListener(this));

        Utils.setJComponentBounds(labelPronunciation, 0.2, 0.2, 0.2, 0.1,
                this.windowWidth, this.windowHeight);
        Utils.setJComponentBounds(characterMap, 0.5, 0.2, 0.2, 0.1,
                this.windowWidth, this.windowHeight);
        Utils.setJComponentBounds(this.charBox, 0.2, 0.3, 0.2, 0.1,
                this.windowWidth, this.windowHeight);
        Utils.setJComponentBounds(this.pronunciationBox, 0.5, 0.3, 0.2, 0.1,
                this.windowWidth, this.windowHeight);
        Utils.setJComponentBounds(editButton, 0.4, 0.5, 0.5, 0.1,
                this.windowWidth, this.windowHeight);
        Utils.setJComponentBounds(this.errorLabel, 0.3, 0.1, 0.5, 0.1,
                this.windowWidth, this.windowHeight);

        this.mainFrame.setLayout(null);
        this.mainFrame.add(labelPronunciation);
        this.mainFrame.add(characterMap);
        this.mainFrame.add(editButton);
        this.mainFrame.add(errorLabel);
        this.mainFrame.add(this.charBox);
        this.mainFrame.add(this.pronunciationBox);

        this.mainFrame.setVisible(true);
    }

    /** Get the pronunciation->character mapping entered by the user in a length-2 String array.
     * where 0-th element is the pronunciation and the index-1 element is the character. **/
    public String[] getMapping() {
        return new String[]{this.pronunciationBox.getText(), this.charBox.getText()};
    }

    /** Get the JTextArea box for the pronunciation. **/
    public JTextArea getPronunciationBox() {
        return this.pronunciationBox;
    }

    /** Get the error message JLabel. **/
    public JLabel getErrorLabel() {
        return this.errorLabel;
    }

    /** Get the PronunciationAndTone object to save the mapping. **/
    public PronunciationAndTone getP() {
        return this.p;
    }

    /** Get the main frame. **/
    public JFrame getMainFrame() {
        return this.mainFrame;
    }



}
