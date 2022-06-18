import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Check entered examples. **/
public class InputCheckerExamples implements InputChecker {

    /**
     * Check the entered example is valid.
     * @param input The example entered by the user
     * @param chineseCharToContain  The character the example should contain.
     * @return  True if the example contains the character and is in the right format, false otherwise.
     */
    @Override
    public boolean checkInput(String input, String chineseCharToContain) {

        if (!input.contains(chineseCharToContain)) {
            return false;
        }

        String patternString = Utils.addRegexCarrotAndDollarSign(Utils.examplePatternString);
        Matcher m = Pattern.compile(patternString).matcher(input);

        return m.find();
    }

}
