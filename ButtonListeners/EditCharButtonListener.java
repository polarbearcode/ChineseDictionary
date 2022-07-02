package ButtonListeners;

import Characters.*;
import Screens.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

/** Listener for the edit character button on the EditCharacter screen. **/
public class EditCharButtonListener extends EditScreenButtons implements ActionListener {

    private CharacterInput characterInputScreen;

    /**
     * Tie button to the EditScreen.
     * @param editScreen    The screen to get the character input.
     */
    public EditCharButtonListener(LookUpCharacter editScreen) {
        super(editScreen);
    }

    /**
     * Open a CharacterInput-like screen with character information already filled in.
     */
    @Override
    public void action() {
        CharacterList charList = new CharacterList(Start.getPathToDictionary());
        ChineseCharacter chineseChar = charList.lookUp(this.editScreen.getCharBox().getText());
        CharacterInput charInput = new CharacterInput(charList);
        this.characterInputScreen = charInput;
        editScreen.setNextScreen(charInput);
        Map<String, JTextArea> charFields = charInput.getInputTextList();

        for (String field : charFields.keySet()) {
            JTextArea inputField = charFields.get(field);
            if (field.equals("Simplified Character")) {
                inputField.setText(chineseChar.getSimplified());
            } else if (field.equals("Traditional Character")) {
                inputField.setText(chineseChar.getTraditional());
            } else if (field.equals("Cantonese Pronunciations")) {
                inputField.setText(String.join(",", chineseChar.getCantonesePronunciation()));
            } else if (field.equals("Pinyin")) {
                inputField.setText(String.join(",", chineseChar.getMandarinPronunciation()));
            } else if (field.equals("Examples")) {
                inputField.setText(String.join(",", chineseChar.getExampleUses()));
            }
        }
    }

    public CharacterInput getCharacterInputScreen() {
        return this.characterInputScreen;
    }


}
