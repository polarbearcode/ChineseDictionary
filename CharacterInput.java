import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

import javax.swing.*;
import java.util.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private Map<String, JTextArea> inputTextList;

    /** The margin percentage relative to all the window edges. **/
    private final double marginPercentage = 0.05;

    /** The margin from the top and bottom window edges for the text area. **/
    private int topAndBotMargin;

    /** The margin from the left and right edges for the text area. **/
    private int leftAndRightMargin;

    /** The label to show error message when user inputs information. **/
    private JLabel errorLabel;

    /** The dictionary where the character inputted will be added to. **/
    private CharacterList characterDictionary;

    /** The labels for the input boxes. **/
    private String[] labelStrings = new String[]{"Examples", "Pinyin", "Cantonese Pronunciations",
            "Traditional Character", "Simplified Character"};

    /**
     * Instantiate a CharacterInput GUI with window size w by h and with 5 input boxes.
     * @param w int, the width of the window
     * @param h int, the height of the windows.
     * @param characterDictionary CharacterList, the character dictionary to save the input to.
     */
    CharacterInput(int w, int h, CharacterList characterDictionary) {
        this.characterDictionary = characterDictionary;
        this.frameWidth = w;
        this.frameHeight = h;
        this.topAndBotMargin =  (int) (this.frameHeight * marginPercentage);
        this.leftAndRightMargin = (int) (this.frameWidth * marginPercentage);
        this.inputTextList = new HashMap<>();
        this.mainFrame = new JFrame("Character Input");
        this.mainFrame.setSize(this.frameWidth, this.frameHeight);
        this.addFiveTextBoxes();
        this.addAddButton();

        this.mainFrame.setLayout(null);
        this.mainFrame.setVisible(true);

        this.errorLabel = new JLabel();
        this.errorLabel.setFont(this.labelFont);
        this.errorLabel.setBounds((int)(this.frameWidth * 0.2), this.topAndBotMargin,
                (int)(this.frameWidth * 0.6), (int)(this.frameHeight * 0.2));
        this.mainFrame.add(this.errorLabel);

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

        for (int i = 0; i <  labelStrings.length; i = i + 1) {
            JLabel label = new JLabel(labelStrings[i]);
            label.setFont(this.labelFont);
            int curHeight = startY - (marginBetweenSections * (i + 1));
            label.setBounds(this.leftAndRightMargin, curHeight, labelWidth, labelHeight);

            JTextArea textInput = new JTextArea();
            textInput.setName(labelStrings[i]);
            textInput.setBounds(this.leftAndRightMargin + labelWidth + marginBetweenLabelAndInput,
                    curHeight, inputWidth, inputHeight);
            textInput.setBackground(Color.cyan);

            this.inputTextList.put(labelStrings[i], textInput);

            this.mainFrame.add(label);
            this.mainFrame.add(textInput);

        }
    }

    /**
     * Add an "Add" button to the main frame so user can save the inputted character.
     */
    private void addAddButton() {
        JButton addButton = new JButton("Add Character");
        addButton.setName("Add Character");
        int yPosition = this.frameHeight - this.topAndBotMargin * 5;
        int buttonWidth = (int) (this.frameWidth * 0.5);
        int buttonHeight = (int) (this.frameHeight * 0.1);
        addButton.setBounds((int)(this.frameWidth * 0.25), yPosition, buttonWidth, buttonHeight);
        addButton.setActionCommand("Add Character");
        addButton.addActionListener(new AddButtonListener());
        this.mainFrame.add(addButton);
    }

    /**
     * Get a copy of inputTextList.
     * @return  A copy of the user input mapping (inputTextList)
     */
    public Map<String, JTextArea> getInputTextList() {

        Map<String, JTextArea> copy = new HashMap<>();
        copy.putAll(this.inputTextList);
        return copy;
    }
    private class AddButtonListener implements  ActionListener {

        /** Regex to check no alphanumeric characters in the simplified/traditional text boxes. **/
        Pattern characterChecker = Pattern.compile("[^a-zA-Z/d]");

        /** To put in characterInfo map. **/
        String simplifiedChar;

        /** To put in characterInfo map. **/
        String traditionalChar;

        /** To put in characterInfo map. **/
        Set<String> cantonPronunciations;

        /** To put in characterInfo map. **/
        Set<String> pinyin;

        /** To put in characterInfo map. **/
        Set<String> examples;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Add Character")) {
                checkForErrors();

                if (errorLabel.getText().equals("")) {
                    processUserInfo();
                    ChineseCharacter newCharacter = new ChineseCharacter(this.simplifiedChar,
                            this.traditionalChar, this.cantonPronunciations, this.pinyin,
                            this.examples);
                    characterDictionary.addCharacter(newCharacter);
                }

            }
        }

        /**
         * Check user input for errors. Add a label to the window if there are errors.
         */
        private void checkForErrors() {
            resetTextboxColors();
            errorLabel.setText("");
            JTextArea errorTextArea = null;
            String errorMessage = "";
            for (String label : inputTextList.keySet()) {
                JTextArea textArea = inputTextList.get(label);
                errorTextArea = textArea;
                String inputtedValue = textArea.getText();
                if (inputtedValue.equals("")) {
                    errorMessage = "Missing " + label;
                    break;
                } else if (label.equals("Simplified Character") || label.equals("Traditional Character"))  {
                    Matcher charMatcher = characterChecker.matcher(inputtedValue);

                    if (!charMatcher.find() || inputtedValue.length() != 1) {
                        errorMessage = "Characters must be length 1 and can not contain alphanumeric";
                        break;
                    }
                }  else if (label.equals("Cantonese Pronunciations")) {
                    Matcher cMatcher = Utils.cantonesePronunciationPattern.matcher(inputtedValue);

                    if (!cMatcher.find()) {
                        errorMessage = "Fix " + label;
                        break;
                    }
                } else if (label.equals("Pinyin")) {
                    Matcher pMatcher = Utils.mandarinPronunciationPattern.matcher(inputtedValue);
                    if (!pMatcher.find()) {
                        errorMessage = "Fix " + label;
                        break;
                    }
                } else {
                    String chineseChar = inputTextList.get("Simplified Character").getText();
                    String example = textArea.getText();
                    if (!example.contains(chineseChar)) {
                        errorMessage = "Example must contain " + chineseChar;
                        break;
                    }
                }
            }

            if (!errorMessage.equals("")) {
                createErrorMessage(errorMessage, errorTextArea);
            }
        }

        /**
         * Take user input from the GUI text boxes and save it to the Character Input class.
         */
        private void processUserInfo() {

            List<String> labelStringsList = Arrays.asList(labelStrings);
            Collections.reverse(labelStringsList);

            for (String label : labelStringsList) {
                String enteredInfo = inputTextList.get(label).getText();
                if (label.equals("Simplified Character")) {
                    simplifiedChar = enteredInfo;
                } else if (label.equals("Traditional Character")) {
                    traditionalChar = enteredInfo;
                } else if (label.equals("Cantonese Pronunciations")) {
                    cantonPronunciations = processPronunciationsExamples(enteredInfo,
                            Utils.cantonesePronunciationPattern, new GetCantonesePronunciation());

                } else if (label.equals("Pinyin")) {
                    pinyin = processPronunciationsExamples(enteredInfo,
                            Utils.mandarinPronunciationPattern, new GetMandarinPronunciation());
                } else {
                    examples = processPronunciationsExamples(enteredInfo,
                            Utils.examplePattern, new InputCheckerExamples(simplifiedChar));
                }

            }

        }

        /**
         * Process the inputted pronunciation or example into a Set.
         * @param enteredInfo   A String for the text entered pronunciation or examples.
         * @param checker   An InputChecker to check the input is in the right format.
         * @param p The Pattern to check the input against.
         * @return Return the processed input as a set.
         */
        private Set<String> processPronunciationsExamples(String enteredInfo, Pattern p, InputChecker checker) {

            Matcher m = p.matcher(enteredInfo);
            Set<String> enteredPronunciations = new HashSet<>();

            while (m.find()) {
                if (!checker.checkInput(m.group(), simplifiedChar)) {
                    continue;
                }
                enteredPronunciations.add(m.group());
            }

            return enteredPronunciations;
        }


        /** Put the error message on the main frame.
         * @param  message  The message to display.
         * @param textArea The JText area with the error**/
        private void createErrorMessage(String message, JTextArea textArea) {
            errorLabel.setText(message);
            textArea.setBackground(Color.RED);

        }

        /**
         * Reset the color for each text box back to the default.
         */
        private void resetTextboxColors() {
            for (String label : inputTextList.keySet()) {
                JTextArea textArea = inputTextList.get(label);
                textArea.setBackground(Color.cyan);
            }
        }
    }

    JFrame getMainFrame() {
        return this.mainFrame;
    }



}

