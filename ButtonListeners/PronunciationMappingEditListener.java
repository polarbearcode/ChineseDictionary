package ButtonListeners;

import Screens.PronunciationEditorScreen;
import Screens.PronunciationMappingEditor;
import Utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PronunciationMappingEditListener implements ActionListener {

    /** The edit screen where the button was pressed. **/
    private PronunciationMappingEditor editScreen;

    public PronunciationMappingEditListener(PronunciationMappingEditor editScreen) {
        this.editScreen = editScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Edit")) {
            if (!checkInput(editScreen.getMapping())) {
                return;
            }

            String[] editScreenMapping = editScreen.getMapping();

            editScreen.getP().addPronunciationMapping(editScreenMapping[0], editScreenMapping[1]);
        }
    }

    /**
     * Check input from the PronunciationEditorScreen is valid.
     * @param input A length two String array that contains the pronunciation and the character.
     * @return  True if input is valid, false otherwise
     */
    private boolean checkInput(String[] input) {

        Pattern nonEnglishChars = Pattern.compile("^[a-zA-z]");
        Matcher pronunciationMatcher = nonEnglishChars.matcher(input[0]);

        Pattern singleCharPattern = Pattern.compile(Utils.individualRegex(Utils.chineseCharacterMatcher));
        Matcher charMatcher = singleCharPattern.matcher(input[1]);

        if (input[0].length() == 0 || pronunciationMatcher.find()) {
            editScreen.getPronunciationBox().setBackground(Color.red);
            editScreen.getErrorLabel().setText("Fix Pronunciation");
            return false;
        } else if (input[1].length() != 1 || !charMatcher.find()) {
            editScreen.getPronunciationBox().setBackground(Color.red);
            editScreen.getErrorLabel().setText("Fix Pronunciation");
            return false;
        } else {
            editScreen.getPronunciationBox().setBackground(Color.cyan);
            editScreen.getErrorLabel().setText("");
            return true;
        }

    }



}