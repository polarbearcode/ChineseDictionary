import java.util.List;

/** Stores two characters: one for pronunciation and one for tone. Like 切韵 **/
public interface PronunciationAndTone {
    /**
     * Returns a combination of characters that equal to the pronunciation of the provided character.
     * @param chineseCharacter The character to find the pronunciation for.
     * @return A list of length 2 arrays where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    public List<String[]> characterCombo(ChineseCharacter chineseCharacter);
}
