import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** Put the character information onto the window. **/
public class CharacterRenderer {

    /** The Character to display information of. **/
    private final ChineseCharacter chineseChar;

    private final int windowWidth = 500;

    private final int windowHeight = 300;

    private boolean showScreen = true;


    /**
     * Initializes a Std Draw window with to display the character information.
     * @param chineseChar The Character to be displayed.
     */
    public CharacterRenderer(ChineseCharacter chineseChar) {
        this.chineseChar = chineseChar;
        StdDraw.setCanvasSize(this.windowWidth, this.windowHeight);
        StdDraw.enableDoubleBuffering();
        drawToScreen();

    }

    public void drawToScreen() {

            boolean showPronunciation = true;

            if (StdDraw.hasNextKeyTyped()) {
                char command = StdDraw.nextKeyTyped();
                if (command == 'h') {
                    showPronunciation = false;
                } else if (command == 'm') {
                    this.showScreen = false;
                    return;
                }
            }

            StdDraw.clear(Color.ORANGE);

            StdDraw.setPenColor(Color.BLACK);
            Font mainFont = new Font("Open Sans, Lucida Sans", Font.PLAIN, 50);
            StdDraw.setFont(mainFont);
            StdDraw.text(0.3, 0.8, this.chineseChar.getSimplified());
            StdDraw.text(0.6, 0.8, this.chineseChar.getTraditional());
            Font englishFont = new Font("Comic Sans MS", Font.PLAIN, 20);
            StdDraw.setFont(englishFont);
            StdDraw.text(0.2, 0.2, "Examples");
            StdDraw.show();
        }
}
