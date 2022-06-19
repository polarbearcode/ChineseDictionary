import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A GUI for looking up characters. **/
public class LookUpCharacter {

    /** The CharacterList to lookup characters in. **/
    private final CharacterList charList;

    /** The GUI main frame. **/
    private JFrame mainFrame;

    /** The JTextArea where user enters a character. Use to get the entered character. **/
    private JTextArea charBox;

    /** The error label. **/
    private JLabel errorLabel;

    /** Instantiate the character lookup GUI.
     * @param charList  The CharacterList to lookup characters in.
     * **/
    public LookUpCharacter(CharacterList charList) {

        this.charList = charList;

        int frameWidth = 500;
        int frameHeight = 500;
        JFrame mainFrame = new JFrame("Character Lookup");
        mainFrame.setSize(frameWidth, frameHeight);

        JLabel charToLookupLabel = new JLabel("Character");
        JButton lookUpButton = new JButton("Look Up");
        JTextArea enterCharBox = new JTextArea();
        JLabel errorLabel = new JLabel();

        charToLookupLabel.setBounds((int)(frameWidth * 0.1), (int)(frameHeight * 0.3),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.05));
        lookUpButton.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.5),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.15));
        enterCharBox.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.3),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.05));
        errorLabel.setBounds((int)(frameWidth * 0.3), (int)(frameHeight * 0.15),
                (int)(frameWidth * 0.6), (int)(frameHeight * 0.15));


        enterCharBox.setName("Character Box");
        this.charBox = enterCharBox;
        this.errorLabel = errorLabel;

        lookUpButton.addActionListener(new LookUpButtonListener());

        mainFrame.setLayout(null);
        mainFrame.add(charToLookupLabel);
        mainFrame.add(lookUpButton);
        mainFrame.add(enterCharBox);
        mainFrame.add(errorLabel);

        this.mainFrame = mainFrame;

        mainFrame.setVisible(true);

    }

    private class LookUpButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Look Up")) {
                charBox.setBackground(Color.WHITE);
                errorLabel.setText("");
                String input = charBox.getText();
                if (!checkCharacterInputValid(input)) {
                    errorLabel.setText("Input must be 1 Chinese character");
                    charBox.setBackground(Color.RED);
                }
            }


        }

        /**
         * Check that the user entered 1 Chinese Character.
         * @param input The text from the character box.
         * @return True if input is 1 Chinese character, false otherwise.
         */
        private boolean checkCharacterInputValid(String input) {
            Matcher m = Pattern.compile(Utils.individualRegex(Utils.chineseCharacterMatcher)).matcher(input);
            return m.find();

        }
    }


    public static void main(String[] args) {
        CharacterList c = new CharacterList("./testDictionary.srl");
        new LookUpCharacter(c);
    }
}
