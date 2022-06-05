import java.util.HashMap;

/** Finds the character combo for the Cantonese Pronunciation. **/
public class CantonesePronunciationAndTone implements PronunciationAndTone {

    /** A HashMap containing the mapping for tones to characters. **/
    private HashMap<Integer, String> toneCharacters;

    /** A HashMap containing the mapping for pronunciation to characters **/
    private HashMap<String, String> pronunCharacters;

    /**
     * Find the character combo for the pronunciation.
     * @param pronunciation String of the pronunciation in the format of "abcd1"
     * @return  A length 2 array containing the characters mapping to the pronunciation and the tone.
     */
    @Override
    public String[] characterCombo(String pronunciation) {

    }
}
