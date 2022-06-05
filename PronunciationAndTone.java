/** Stores two characters: one for pronunciation and one for tone. Like 切韵 **/
public interface PronunciationAndTone {
    /**
     * Returns a combination of characters that equal the provided pronunciation.
     * @param pronunciation String of the pronunciation in the format of "abcd1"
     * @return A length 2 array where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    public String[] characterCombo(String pronunciation);
}
