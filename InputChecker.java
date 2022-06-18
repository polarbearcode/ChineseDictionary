/** Checks inputs are valid.  **/
public interface InputChecker {
    /** Checks that the provided input is valid. **/
    public boolean checkInput(String input, String chineseCharToContain);

    /**
     * Return a String to set the error message to if the user input is not valid.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @return  A String for the error message label or an empty string if the input is valid.
     */
    public String setErrorMessage(String userInput);
}
