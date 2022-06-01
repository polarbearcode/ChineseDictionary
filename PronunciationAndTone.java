/** Stores two characters: one for pronunciation and one for tone. Like 切韵 **/
public class PronunciationAndTone {

    /** The character representing the pronunciation. **/
    private String pronunciationChar;

    /** The character representing the tone. **/
    private String toneChar;

    /**
     * Instantiate with the provided pronunciation and tone.
     * @param pronunciationChar String for the pronunciation character.
     * @param toneChar  String for the tone character.
     */
    PronunciationAndTone(String pronunciationChar, String toneChar) {
        this.pronunciationChar = pronunciationChar;
        this.toneChar = toneChar;
    }

    /** Get the pronunciation character. **/
    public String getPronunciationChar() {
        return this.pronunciationChar;
    }

    /** Get the tone character. **/
    public String getToneChar() {
        return this.toneChar;
    }
}
