import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Get the Cantonese pronunciation of a character.  **/
public class GetCantonesePronunciation implements GetPronunciationLanguage, InputChecker{

    /**
     * Get the Cantonese pronunciation of the character.
     * @param chineseChar The ChineseCharacter to get the Cantonese pronunciation of.
     * @return  The Set of Cantonese pronunciations for the character.
     */
    @Override
    public Set<String> getPronunciation(ChineseCharacter chineseChar) {
        return chineseChar.getCantonesePronunciation();
    }

    /**
     * Check the input that the user entered is in the right format.
     * @param input The pronunciation the user entered.
     * @return  True if the input is in the right format, false otherwise.
     */
    @Override
    public boolean checkInput(String input) {
        String patternString = Utils.addRegexCarrotAndDollarSign(Utils.cantonesePronunciationPatternString);
        Matcher m = Pattern.compile(patternString).matcher(input);
        return m.find();
    }
}
