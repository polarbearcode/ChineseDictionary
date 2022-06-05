import java.util.HashMap;
import java.util.List;

/** Finds the character combo for the Cantonese Pronunciation. **/
public class CantonesePronunciationAndTone implements PronunciationAndTone {

    /** A HashMap containing the mapping for tones to characters. **/
    private HashMap<Integer, String> toneCharacters;

    /** A HashMap containing the mapping for pronunciation to characters **/
    private HashMap<String, String> pronunCharacters;

    /**
     * Returns a combination of characters that equal to the pronunciation of the provided character.
     * @param chineseCharacter The character to find the pronunciation for.
     * @return A list of length 2 arrays where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    @Override
    public List<String[]> characterCombo(ChineseCharacter chineseCharacter) {

    }
}
