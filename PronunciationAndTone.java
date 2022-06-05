/** Stores two characters: one for pronunciation and one for tone. Like 切韵 **/
public interface PronunciationAndTone {
    /**
     * Returns a combination of characters that equal to the pronunciation of the provided character.
     * @param chineseCharacter The character to find the pronunciation for.
     * @return A length 2 array where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    public String[] characterCombo(ChineseCharacter chineseCharacter);
}
