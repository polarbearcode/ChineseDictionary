import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** Put the character information onto the window. **/
public class CharacterRenderer {

    private final int windowWidth = 500;

    private final int windowHeight = 300;

    private static boolean showScreen = true;

    static boolean showPronunciation = true;


    /**
     * Display information from the chinese character
     * @param chineseChar The character to display information.
     */
    public static void drawToScreen(ChineseCharacter chineseChar) {
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                if (StdDraw.hasNextKeyTyped()) {
                    char command = StdDraw.nextKeyTyped();
                    if (command == 'h') {
                        showPronunciation = !showPronunciation;
                    } else if (command == 'm') {
                        showScreen = false;
                        return;
                    }
                }

                StdDraw.clear(Color.ORANGE);

                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setFont(Utils.chineseFont);
                StdDraw.text(0.3, 0.8, chineseChar.getSimplified());
                StdDraw.text(0.6, 0.8, chineseChar.getTraditional());
                Font englishFont = new Font("Comic Sans MS", Font.PLAIN, 20);
                StdDraw.setFont(englishFont);
                StdDraw.text(0.2, 0.2, "Examples");
                StdDraw.show();
            }
        }
    }

    public static void main(String[] args) {
        CharacterList c = new CharacterList("./testDictionary.srl");
        CharacterRenderer.drawToScreen(c.lookUp("种"));

    }
}
