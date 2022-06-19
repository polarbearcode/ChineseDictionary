import javax.swing.*;

/** A GUI for looking up characters. **/
public class LookUpCharacter {

    public static void main(String[] args) {
        int frameWidth = 500;
        int frameHeight = 500;
        JFrame mainFrame = new JFrame("Character Lookup");
        mainFrame.setSize(frameWidth, frameHeight);

        JLabel charToLookupLabel = new JLabel("Character");
        JButton lookUpButton = new JButton("Look Up");
        JTextArea enterCharBox = new JTextArea();

        charToLookupLabel.setBounds((int)(frameWidth * 0.1), (int)(frameHeight * 0.3),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.05));
        lookUpButton.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.5),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.15));
        enterCharBox.setBounds((int)(frameWidth * 0.25), (int)(frameHeight * 0.3),
                (int)(frameWidth * 0.3), (int)(frameHeight * 0.05));

        mainFrame.setLayout(null);
        mainFrame.add(charToLookupLabel);
        mainFrame.add(lookUpButton);
        mainFrame.add(enterCharBox);

        mainFrame.setVisible(true);
    }
}
