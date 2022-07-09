package Utils;

import Characters.ChineseCharacter;
import Test.TestChineseCharacter;
import edu.princeton.cs.introcs.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    /**
     * Create a font for Chinese characters with the specified size.
     * @param size  int, size of the font.
     * @return A Font object.
     */
    public static Font createChineseFont(int size) {
        return new Font("Open Sans, Lucida Sans", Font.PLAIN, size);
    }

    /**
     * Create a font for English text.
     * @param size  The size of the font.
     * @return  A Comic Sans font.
     */
    public static Font comicSansFont(int size) {
        return new Font("Comic Sans MS", Font.PLAIN, size);
    }

    /**
     * Set position and size of component relative the the frame size.
     * @param component The component (JTextArea, JLabel, JButton).
     * @param xPos  Where on the screen along horizontal axis the component should go given as a percentage.
     * @param yPos  Where on the screen along vertical axis the component should go given as a percentage.
     * @param width The width as a percentage of the screen width.
     * @param height The height as a percentage of the screen height.
     * @param frameWidth The screen width.
     * @param frameHeight  The screen height.
     */
    public static void setJComponentBounds(JComponent component, double xPos, double yPos, double width,
                                           double height, int frameWidth, int frameHeight) {
        component.setBounds((int) (frameWidth * xPos), (int)(frameHeight * yPos),
                (int)(frameWidth * width), (int)(frameHeight * height));

    }

    /** Wait until a key is typed to get the next command. **/
    public static char getNextCommand() {

        while (!StdDraw.hasNextKeyTyped()) {
            continue;
        }

        return StdDraw.nextKeyTyped();
    }

    public static ChineseCharacter createDefaultChar() {

        ChineseCharacter 種 = new ChineseCharacter("种", "種",
                new HashSet<>(Arrays.asList("zung2", "zung3")),
                new HashSet<>(Arrays.asList("zhong3", "zhong4")),
                new HashSet<>(Arrays.asList("种群", "种植")));

        return 種;
    }

}
