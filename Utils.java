import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

public class Utils {

    /** String to use for cantonesePronunciationPattern. **/
    public static final String cantonesePronunciationPatternString = "([a-zA-Z]+)([1-6])";

    /** String to use for mandarinPronunciationPattern. **/
    public static final String pinyinPronunciationPatternString = "([a-zA-Z]+)([1-4]?)";


    /** Pattern to check Cantonese pronunciations. **/
    public static final Pattern cantonesePronunciationPattern = Pattern.compile(cantonesePronunciationPatternString);

    /** Pattern to check Pinyin. **/
    public static final Pattern mandarinPronunciationPattern = Pattern.compile(pinyinPronunciationPatternString);

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
     * Add ^ and $ to the pattern string so that it becomes ^patternString$ for checking inputs.
     * @param patternString The pattern string to change.
     * @return  Return a String in the format of  ^patternString$
     */
    public static String addRegexCarrotAndDollarSign(String patternString) {
        return "^" + patternString + "$";
    }

}
