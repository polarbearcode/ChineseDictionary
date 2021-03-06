package Screens;

import Characters.CharacterList;
import Characters.ChineseCharacter;

import java.util.Iterator;

/** A Character Renderer specifically used for the flash cards. Has an extra command for
 * shuffling through the characters.
 */
public class FlashCardCharRender extends CharacterRenderer implements DictionaryScreen{


    /** The FlashCard object. **/
    private FlashCards flashCards;

    /** The flash card iterator. **/
    private Iterator<FlashCards.CharLinkedList.CharNode> flashCardIterator;

    /** The char list the flash cards are tied to. **/
    private CharacterList charList;

    /**
     * Instantiate a Character Renderer for the Chinese character tied to the character list.
     * @param charList  The Character List the character is tied to.
     */
    public FlashCardCharRender(CharacterList charList) {
        super();
        flashCards = new FlashCards(charList);
        this.flashCardIterator = this.flashCards.iterator();
        this.charList = charList;

    }

    /**
     * Process the user command.
     * @param command The char entered.
     */
    @Override
    public void processCommand(char command){
        if (command == 'h') {
            this.switchShowPronunciation();
        } else if (command == 'm') {
            this.setShowScreen(false);
        } else if (command == 'q') {
            Start.setShowScreen(false);
        } else if (this.getShowScreen() && this.getPronunciationCommands().containsKey(command)) {
            this.playFile(command);
        } else if (command == 'n') {
            if (this.flashCardIterator.hasNext()) {
                ChineseCharacter chineseChar = this.charList.lookUp(this.flashCardIterator.next().curchar);
                this.setChineseChar(chineseChar);
            }
        }
    }

    private static class FlashCards  {


        /** The characters in the charlist as a list. **/
        CharLinkedList charsInLinkedList;


        /**
         * Instantiate a Flash Card Screen tied to the character list.
         * @param charList  The CharacterList containing the characters to display.
         */
        public FlashCards(CharacterList charList) {
            charsInLinkedList = new CharLinkedList();

            for (String chineseChar : charList.getCurrentDictionary().keySet()) {
                charsInLinkedList.add(chineseChar);
            }
        }

        public Iterator<CharLinkedList.CharNode> iterator() {
            return this.charsInLinkedList.iterator();
        }




        /** Use to put characters in a list and cycle through them. **/
        private static class CharLinkedList implements Iterable<CharLinkedList.CharNode> {


            /** The last node. **/
            private CharNode last;

            /** Boolean indicating if list is empty. **/
            private boolean isEmpty;

            private CharNode sentinel;

            private CharLinkedList() {
                this.sentinel = new CharNode("X", null);
                this.sentinel.next = this.sentinel;
                this.last = sentinel;
                this.isEmpty = true;
            }



            private CharLinkedList(String chineseChar) {
                this();
                this.add(chineseChar);
            }


            /** Add a character to the end of the list. **/
            private void add(String chineseChar) {

               this.last.next =  new CharNode(chineseChar, this.sentinel.next);
               this.last = this.last.next;
               this.isEmpty = false;
            }


            @Override
            public Iterator<CharLinkedList.CharNode> iterator() {
                return new CharLinkedList.CharLinkedListIterator(this.sentinel.next);
            }

            private class CharLinkedListIterator implements Iterator<CharNode> {

                private CharNode start;

                CharLinkedListIterator(CharNode start) {
                    this.start = start;
                }

                @Override
                public boolean hasNext() {
                    return !isEmpty;
                }

                @Override
                public CharNode next() {

                    CharNode toReturn = start.next;
                    start = start.next;
                    return toReturn;
                }
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
}
