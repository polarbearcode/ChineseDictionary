/** Checks inputs are valid.  **/
public interface InputChecker {
    /**
     * Check that the input is valid.
     * @param input What the user entered as a String.
     * @param chineseCharToContain    The Chinese character that the input is for as a string.
     * @return True if input is valid, false otherwise.
     */
    public boolean checkInput(String input, String chineseCharToContain);

    /**
     * Return a String to set the error message to if the user input is not valid.
     * @param userInput What the user inputs in a textbox in the GUI as a string.
     * @param label The label of the textbox.
     * @return  A String for the error message label or an empty string if the input is valid.
     */
    public String setErrorMessage(String userInput, String label);
}
