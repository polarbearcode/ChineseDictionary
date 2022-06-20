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
        String patternString = Utils.individualRegex(Utils.cantonesePronunciationPatternString);
        Matcher m = Pattern.compile(patternString).matcher(input);
        return m.find();
    }

    /**
     * Check if Cantonese pronunciation box is empty.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @param label The label of the text box.
     * @return A String "Fix Cantonese Pronunciation" if the Cantonese pronunciation box is empty.
     * Return an empty string otherwise.
     */
    @Override
    public String setErrorMessage(String userInput, String label) {
        if (userInput.isEmpty()) {
            return "Fix Cantonese Pronunciation";
        } else {
            return "";
        }
    }
}
