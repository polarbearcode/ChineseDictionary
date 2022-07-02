package Screens;

import Characters.CharacterList;
import ButtonListeners.*;
import Utils.*;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A GUI for looking up characters. **/
public class LookUpCharacter implements DictionaryScreen {

    /**
     * The Characters.CharacterList to lookup characters in.
     **/
    private final CharacterList charList;

    /**
     * The GUI main frame.
     **/
    private JFrame mainFrame;

    /**
     * The JTextArea where user enters a character. Use to get the entered character.
     **/
    private JTextArea charBox;

    /**
     * The error label.
     **/
    private JLabel errorLabel;

    /**
     * Lookup button.
     **/
    private JButton lookUpButton;


    private DictionaryScreen nextScreen;

    /**
     * Instantiate the character lookup GUI.
     *
     * @param charList The Characters.CharacterList to lookup characters in.
     **/
    public LookUpCharacter(CharacterList charList) {

        this.charList = charList;
        this.mainFrame = new JFrame("Character Lookup");
    }


    @Override
    public void drawToScreen() {
        int frameWidth = 500;
        int frameHeight = 500;
        mainFrame.setSize(frameWidth, frameHeight);

        JLabel charToLookupLabel = new JLabel("Character");
        JButton lookUpButton = new JButton("Look Up");
        JTextArea enterCharBox = new JTextArea();
        JLabel errorLabel = new JLabel();

        charToLookupLabel.setBounds((int) (frameWidth * 0.1), (int) (frameHeight * 0.3),
                (int) (frameWidth * 0.3), (int) (frameHeight * 0.05));
        lookUpButton.setBounds((int) (frameWidth * 0.25), (int) (frameHeight * 0.5),
                (int) (frameWidth * 0.3), (int) (frameHeight * 0.15));
        enterCharBox.setBounds((int) (frameWidth * 0.25), (int) (frameHeight * 0.3),
                (int) (frameWidth * 0.3), (int) (frameHeight * 0.05));
        errorLabel.setBounds((int) (frameWidth * 0.3), (int) (frameHeight * 0.15),
                (int) (frameWidth * 0.6), (int) (frameHeight * 0.15));


        enterCharBox.setName("Character Box");
        this.charBox = enterCharBox;
        this.errorLabel = errorLabel;
        this.lookUpButton = lookUpButton;


        lookUpButton.addActionListener(new LookUpButtonListener(this));
        lookUpButton.setName("lookup");

        mainFrame.setLayout(null);
        mainFrame.add(charToLookupLabel);
        mainFrame.add(lookUpButton);
        mainFrame.add(enterCharBox);
        mainFrame.add(errorLabel);

        this.mainFrame = mainFrame;

        mainFrame.setVisible(true);

    }


    /**
     * Check that the user entered 1 Chinese Character.
     * @param input The text from the character box.
     * @return True if input is 1 Chinese character, false otherwise.
     */
    public boolean checkCharacterInputValid(String input) {
        Matcher m = Pattern.compile(Utils.individualRegex(Utils.chineseCharacterMatcher)).matcher(input);
        return m.find();

    }

    /**
     * Get the next screen either Input or Renderer.
     * @return The nextScreen instance variable.
     */
    public DictionaryScreen getNextScreen() {
        return this.nextScreen;
    }





    public static void main(String[] args) {
        CharacterList c = new CharacterList("./testDictionary.srl");
        LookUpCharacter lookUpCharacter = new LookUpCharacter(c);
    }

    public JLabel getErrorLabel() {
        return this.errorLabel;
    }

    public JTextArea getCharBox() {
        return this.charBox;
    }

    public CharacterList getCharList() {
        return this.charList;
    }

    JButton getLookUpButton() {
        return this.lookUpButton;
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public void setNextScreen(DictionaryScreen ds) {
        this.nextScreen = ds;
    }

    /**
     * Add a componenet to the main frame.
     * @param component The component to add.
     */
    void addToMainFrame(JComponent component) {
        this.mainFrame.add(component);
    }


    public void hideMainFrame() {
        this.mainFrame.setVisible(false);
    }
}
