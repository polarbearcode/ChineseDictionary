import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

/** Listener for the edit character button on the EditCharacter screen. **/
public class EditCharButtonListener extends EditScreenButtons implements ActionListener {

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
        Map<String, JTextArea> charFields = charInput.getInputTextList();

        for (String field : charFields.keySet()) {
            JTextArea inputField = charFields.get(field);
            if (field.equals("Simplified Character")) {
                inputField.setText(chineseChar.getSimplified());
            } else if (field.equals("Traditional Character")) {
                inputField.setText(chineseChar.getTraditional());
            } else if (field.equals("Cantonese Pronunciations")) {

            }
        }
    }

    /**
     * Take the set and return it's items as a single string separated by a comma.
     * @param info  Set containing the items
     * @return A String in the format of "a1, a2, ..."
     */
    public String turnSetIntoInput(Set<String> info) {

    }
}
