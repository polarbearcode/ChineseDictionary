package Screens;

import Characters.CharacterList;

/** The actual display for the flash cards. User can use arrows to go through the characters. **/
public class FlashCards implements DictionaryScreen {

    CharacterList charList;

    /** The characters in the charlist as a list. **/


    /**
     * Instantiate a Flash Card Screen tied to the character list.
     * @param charList  The CharacterList containing the characters to display.
     */
    public FlashCards(CharacterList charList) {
        this.charList = charList;
    }

    @Override
    public void drawToScreen() {

    }

    /** Use to put characters in a list and cycle through them. **/
    private static class CharLinkedList {

    }

}
