import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Get the pinyin of a character.  **/
public class GetMandarinPronunciation implements GetPronunciationLanguage, InputChecker {

    @Override
    public Set<String> getPronunciation(ChineseCharacter chineseChar) {
        return chineseChar.getMandarinPronunciation();
    }

    /**
     * Check the input that the user entered is in the right format.
     * @param input The pronunciation the user entered.
     * @param chineseCharToContain Parameter not used.
     * @return  True if the input is in the right format, false otherwise.
     */
    @Override
    public boolean checkInput(String input, String chineseCharToContain) {
        String patternString = Utils.addRegexCarrotAndDollarSign(Utils.pinyinPronunciationPatternString);
        Matcher m = Pattern.compile(patternString).matcher(input);
        return m.find();
    }

    /**
     * Check if pinyin pronunciation box is empty.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @param label The label of the text box.
     * @return A String "Fix Pinyin" if the Pinyin pronunciation box is empty.
     * Return an empty string otherwise.
     */
    @Override
    public String setErrorMessage(String userInput, String label) {
        if (userInput.isEmpty()) {
            return "Fix Pinyin Pronunciation";
        } else {
            return "";
        }
    }
}
