import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterInput {

    /** Font for the text box labels on the left side. **/
    private final Font labelFont = new Font("Comic Sans MS", Font.BOLD, 16);

    /** The width of the GUI window. **/
    private int frameWidth;

    /** The height of the GUI windows. **/
    private int frameHeight;

    /** The JFrame for the character input. **/
    private JFrame mainFrame;

    /** Save the JTextAreas to get the information. **/
    private List<JTextArea> inputTextList;

    /** The margin percentage relative to all the window edges. **/
    private final double marginPercentage = 0.05;

    /** The margin from the top and bottom window edges for the text area. **/
    private int topAndBotMargin;

    /** The margin from the left and right edges for the text area. **/
    private int leftAndRightMargin;

    /**
     * Instantiate a CharacterInput GUI with window size w by h and with 5 input boxes.
     * @param w int, the width of the window
     * @param h int, the height of the windows.
     */
    CharacterInput(int w, int h) {
        this.frameWidth = w;
        this.frameHeight = h;
        this.topAndBotMargin =  (int) (this.frameHeight * marginPercentage);
        this.leftAndRightMargin = (int) (this.frameWidth * marginPercentage);
        this.inputTextList = new ArrayList<>();
        this.mainFrame = new JFrame("Character Input");
        this.mainFrame.setSize(this.frameWidth, this.frameHeight);
        this.addFiveTextBoxes();
        this.addAddButton();
        this.mainFrame.setLayout(null);
        this.mainFrame.setVisible(true);
    }

    /**
     * Add the 5 required text boxes and their labels (simplified char, traditional char, cantonese pronunciation,
     * pinyin, and examples to the main frame of this.
     */
    private void addFiveTextBoxes() {

        double labelWidthProportion = 0.4;
        double labelHeightProportion = 0.07;
        double inputWidthProportion = 0.45;
        double inputHeightProportion = 0.07;
        double marginBetweenLabelAndBoxProportion = 0.05;
        double marginBetweenSectionsProportion = 0.1;



        int labelWidth = (int)(this.frameWidth * labelWidthProportion);
        int labelHeight = (int) (this.frameHeight * labelHeightProportion);

        int inputWidth = (int)(this.frameWidth * inputWidthProportion);
        int inputHeight = (int)(this.frameHeight * inputHeightProportion);
        int marginBetweenLabelAndInput = (int)(this.frameWidth * marginBetweenLabelAndBoxProportion);
        int marginBetweenSections = (int)(this.frameHeight * marginBetweenSectionsProportion);

        int startY = frameHeight - this.topAndBotMargin * 6;

        String[] labelStrings = new String[]{"Examples", "Pinyin", "Cantonese Pronunciations",
                "Traditional Character", "Simplified Character"};


        for (int i = 0; i <  labelStrings.length; i = i + 1) {
            JLabel label = new JLabel(labelStrings[i]);
            label.setFont(this.labelFont);
            int curHeight = startY - (marginBetweenSections * (i + 1));
            label.setBounds(this.leftAndRightMargin, curHeight, labelWidth, labelHeight);

            JTextArea textInput = new JTextArea();
            textInput.setBounds(this.leftAndRightMargin + labelWidth + marginBetweenLabelAndInput,
                    curHeight, inputWidth, inputHeight);
            textInput.setBackground(Color.cyan);

            this.inputTextList.add(textInput);

            this.mainFrame.add(label);
            this.mainFrame.add(textInput);

        }
    }

    /**
     * Add an "Add" button to the main frame so user can save the inputted character.
     */
    private void addAddButton() {
        JButton addButton = new JButton("Add Character");
        int yPosition = this.frameHeight - this.topAndBotMargin * 5;
        int buttonWidth = (int) (this.frameWidth * 0.5);
        int buttonHeight = (int) (this.frameHeight * 0.1);
        addButton.setBounds((int)(this.frameWidth * 0.25), yPosition, buttonWidth, buttonHeight);
        this.mainFrame.add(addButton);
    }



    public static void main(String[] args) {

        /**
        JFrame mainFrame = new JFrame("Character Input");
        mainFrame.setSize(400,400);

        mainFrame.getContentPane().setBackground(Color.YELLOW);

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 16);

        JLabel simplifiedChar = new JLabel("Simplified Character");
        simplifiedChar.setFont(labelFont);
        simplifiedChar.setBounds(50, 30, 250, 20);

        JTextArea simplifiedCharacterText = new JTextArea("");
        simplifiedCharacterText.setBounds(250, 30, 100, 20);
        simplifiedCharacterText.setBackground(Color.cyan);

        JLabel tradChar = new JLabel("Traditional Character");
        tradChar.setFont(labelFont);
        tradChar.setBounds(50, 60, 250, 20);

        JTextArea tradCharText = new JTextArea("");
        tradCharText.setBounds(250, 60, 100, 20);
        tradCharText.setBackground(Color.cyan);


        mainFrame.add(simplifiedCharacterText);
        mainFrame.add(simplifiedChar);
        mainFrame.add(tradChar);
        mainFrame.add(tradCharText);

        mainFrame.setLayout(null);

        mainFrame.setVisible(true); **/

        new CharacterInput(500, 500);

    }

}

