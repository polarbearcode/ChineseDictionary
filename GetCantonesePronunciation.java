import java.util.Set;

/** Get the Cantonese pronunciation of a character.  **/
public class GetCantonesePronunciation implements GetPronunciationLanguage{

    /**
     * Get the Cantonese pronunciation of the character.
     * @param chineseChar
     * @return
     */
    @Override
    public Set<String> getPronunciation(ChineseCharacter chineseChar) {
        return chineseChar.getCantonesePronunciation();
    }
}
