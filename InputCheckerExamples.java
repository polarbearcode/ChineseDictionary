import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Check entered examples. **/
public class InputCheckerExamples implements InputChecker {

    /** The Chinese character the input checker is tied to. **/
    private String chineseChar;

    /**
     * Instantiate an example input checker tied to the specified chinese character.
     * @param chineseChar   The Chinese character this input checker is tied to.
     */
    InputCheckerExamples(String chineseChar) {
        this.chineseChar = chineseChar;
    }

    /**
     * Check the entered example is valid.
     * @param input The example entered by the user
     * @return  True if the example contains the character and is in the right format, false otherwise.
     */
    @Override
    public boolean checkInput(String input) {

        if (!input.contains(this.chineseChar)) {
            return false;
        }

        String patternString = Utils.individualRegex(Utils.examplePatternString);
        Matcher m = Pattern.compile(patternString).matcher(input);

        return m.find();
    }

    /**
     * Check if the examples input is valid.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @param label The label of the textbox.
     * @return String, "Fix examples" if there are no examples entered. Return "" otherwise.
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
