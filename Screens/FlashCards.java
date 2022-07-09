package Screens;

import Characters.CharacterList;
import Characters.ChineseCharacter;

import java.util.LinkedList;

/** The actual display for the flash cards. User can use arrows to go through the characters. **/
public class FlashCards implements DictionaryScreen {

    CharacterList charList;

    /** The characters in the charlist as a list. **/
    CharLinkedList charsInLinkedList;


    /**
     * Instantiate a Flash Card Screen tied to the character list.
     * @param charList  The CharacterList containing the characters to display.
     */
    public FlashCards(CharacterList charList) {
        this.charList = charList;
        charsInLinkedList = new CharLinkedList();

        for (String chineseChar : this.charList.getCurrentDictionary().keySet()) {
            charsInLinkedList.add(chineseChar);
        }

    }

    @Override
    public void drawToScreen() {

    }

    /** Use to put characters in a list and cycle through them. **/
    private static class CharLinkedList {

        /** Sentinel node. **/
        private CharNode sentinel;


        /**
         * Instantiate an empty list.
         */
        private CharLinkedList() {
            this.sentinel = new CharNode("X", null);
            this.sentinel.next.next = this.sentinel;
        }

        private CharLinkedList(String chineseChar) {
            this();
            this.sentinel.next = new CharNode(chineseChar, this.sentinel);
        }


        /** Add a character to the end of the list. **/
        private void add(String chineseChar) {
            CharNode p = this.sentinel;

            while (p.next != this.sentinel) {
                p = p.next;
            }

            p.next = new CharNode(chineseChar, this.sentinel);
        }

        /** Get the first node. **/
        private CharNode getFirst() {
            return this.sentinel.next;
        }


        private static class CharNode {
            private String curchar;
            private CharNode next;

            public CharNode(String curChar) {
                this.curchar = curChar;
                this.next = null;
            }

            public CharNode(String curChar, CharNode next) {
                this.curchar = curChar;
                this.next = next;
            }
        }


    }

}
