package Screens;

import Characters.CharacterList;
import Characters.ChineseCharacter;

/** A Character Renderer specifically used for the flash cards. Has an extra command for
 * shuffling through the characters.
 */
public class FlashCardCharRender extends CharacterRenderer{

    /** The character list **/
    private CharacterList charList;

    /**
     * Instantiate a Character Renderer for the Chinese character tied to the character list.
     * @param chineseChar   The character to display on the current screen.
     * @param charList  The Character List the character is tied to.
     */
    public FlashCardCharRender(ChineseCharacter chineseChar, CharacterList charList) {
        super(chineseChar);
        this.charList = charList;
    }

    /**
     * Process the user command.
     * @param command   The char entered.
     */
    private void processCommand(char command){
        if (command == 'h') {
            this.setShowScreen(!this.getShowScreen());
        } else if (command == 'm') {
            this.setShowScreen(false);
            return;
        } else if (command == 'q') {
            Start.setShowScreen(false);
            return;
        } else if ( this.getShowScreen() && this.getPronunciationCommands().containsKey(command)) {
            this.playFile(command);
        } else if (command == 'n') {

        }
    }
}
