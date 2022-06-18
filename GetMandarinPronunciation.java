import java.util.Set;

/** Get the pinyin of a character.  **/
public class GetMandarinPronunciation implements GetPronunciationLanguage {

    @Override
    public Set<String> getPronunciation(ChineseCharacter chineseChar) {
        return chineseChar.getMandarinPronunciation();
    }
}
