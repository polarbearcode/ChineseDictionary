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

        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        Font mainFont = new Font("Times New Roman", Font.PLAIN, 30);
        StdDraw.setFont(mainFont);
        //StdDraw.text(this.windowWidth * 0.5, this.windowHeight * 0.5, this.chineseChar.getSimplified());
        StdDraw.text(200, 200, "green");
        StdDraw.show();

    }
}
