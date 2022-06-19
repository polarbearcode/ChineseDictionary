import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** check if the inputs for Simplified and Traditional characters are valid. **/
public class CharacterInputChecker implements InputChecker{

    /**
     * Check if the character inputs are valid.
     * @param input What the user entered as a String.
     * @return True if the character input only contains 1 Chinese character.
     */
    @Override
    public boolean checkInput(String input) {
        Matcher m = Pattern.compile(Utils.addRegexCarrotAndDollarSign(Utils.chineseCharacterMatcher)).matcher(input);
        return m.find();
    }

    /**
     * Return an error message if there are errors with the character inputs.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @param label The label of the text box.
     * @return If input is not valid, String, either "Fix Simplified Character" or "Fix Traditional Character".
     * Return "" if input is valid.
     */
    @Override
    public String setErrorMessage(String userInput, String label) {
        if (!checkInput(userInput)) {
            return "Fix " + label;
        } else {
            return "";
        }
    }
}
