import java.awt.*;

/** Put the character information onto the window. **/
public class CharacterRenderer {

    /** The Character to display information of. **/
    private final ChineseCharacter chineseChar;

    private final int windowWidth = 500;

    private final int windowHeight = 300;





    /**
     * Initializes a Std Draw window with to display the character information.
     * @param chineseChar The Character to be displayed.
     */
    public CharacterRenderer(ChineseCharacter chineseChar) {
        this.chineseChar = chineseChar;

        StdDraw.setCanvasSize(this.windowWidth, this.windowHeight);
        StdDraw.clear(Color.ORANGE);

        drawToScreen();
    }

    public void drawToScreen() {
        
        StdDraw.setPenColor(Color.BLACK);
        Font mainFont = new Font("Open Sans, Lucida Sans", Font.PLAIN, 50);
        StdDraw.setFont(mainFont);
        StdDraw.text(0.3, 0.6, this.chineseChar.getSimplified());
        StdDraw.text(0.6, 0.6, this.chineseChar.getTraditional());
        StdDraw.show();

    }
}
