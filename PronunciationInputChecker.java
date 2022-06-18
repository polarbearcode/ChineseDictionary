import java.util.regex.Matcher;

/** Check the inputted pronunciation is valid. **/
public class PronunciationInputChecker implements InputChecker {

    /**
     * Check that the input is in the format of "aaa1";
     * @param input The pronunciation entered by the user.
     * @return  True if input is in the correct format, false otherwise.
     */
    @Override
    public boolean checkInput(String input) {
        Matcher m = Utils.cantonesePronunciationPattern.matcher(input);
        return m.find();
    }
}
