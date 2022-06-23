import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** Put the character information onto the window. **/
public class CharacterRenderer implements DictionaryScreen {

    /** The character whose information will be shown. **/
    private final ChineseCharacter chineseChar;

    private final int windowWidth = 500;

    private final int windowHeight = 300;

    private boolean showScreen = true;

    private boolean showPronunciation = true;

    CharacterRenderer(ChineseCharacter chineseChar) {
        this.chineseChar = chineseChar;
    }


    /**
     * Display information from the chinese character
     */
    @Override
    public void drawToScreen() {
        while (true) {
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

                while (!StdDraw.hasNextKeyTyped()) {
                    continue;
                }
            }
        }

    }
