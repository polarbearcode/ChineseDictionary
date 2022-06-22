import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Utils {

    /** String to use for cantonesePronunciationPattern. **/
    public static final String cantonesePronunciationPatternString = "([a-zA-Z]+)([1-6])";

    /** String to use for mandarinPronunciationPattern. **/
    public static final String pinyinPronunciationPatternString = "([a-zA-Z]+)([1-4]?)";

    /** String to use for a pattern to match all Chinese characters. **/
    public static final String chineseCharacterMatcher = "[一-龥]";

    /** String to use for examplePattern. **/
    public static final String examplePatternString = "([一-龥]{2,})+";


    /** Pattern to check Cantonese pronunciations. **/
    public static final Pattern cantonesePronunciationPattern = Pattern.compile(cantonesePronunciationPatternString);

    /** Pattern to check Pinyin. **/
    public static final Pattern mandarinPronunciationPattern = Pattern.compile(pinyinPronunciationPatternString);

    /** Regex to check for the example uses. **/
    public static final Pattern examplePattern = Pattern.compile(examplePatternString);

    /** Font for Chinese characters. **/
    public static Font chineseFont = new Font("Open Sans, Lucida Sans", Font.PLAIN, 50);

    /**
     * Write the object to the file specified by file path.
     * @param filePath  String path to the file
     * @param o The Object to save to the file.
     */
    public static void writeFile(String filePath, Object o) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Add ^ and $ to the pattern string so that it becomes ^patternString$ for checking individual inputs.
     * @param patternString The pattern string to change.
     * @return  Return a String in the format of  ^patternString$
     */
    public static String individualRegex(String patternString) {
        return "^" + patternString + "$";
    }

}
