package Screens;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Set;

import Characters.*;
import Utils.*;


/** Put the character information onto the window. **/
public class CharacterRenderer implements DictionaryScreen {

    /** The character whose information will be shown. **/
    private final ChineseCharacter chineseChar;

    private final int windowWidth = 500;

    private final int windowHeight = 300;


    private boolean showScreen = true;

    private boolean showPronunciation = true;



    public CharacterRenderer(ChineseCharacter chineseChar) {
        this.chineseChar = chineseChar;
    }


    /**
     * Display information from the chinese character
     */
    @Override
    public void drawToScreen() {

        double simplifiedSide = 0.3;
        double traditionalSide = 0.6;
        double characterDrawY = 0.8;

        this.drawTopScreen(simplifiedSide, traditionalSide, characterDrawY);
        this.drawBotScreen();

        while (true) {

                char command = Utils.getNextCommand();

                this.drawTopScreen(simplifiedSide, traditionalSide, characterDrawY);

                if (command == 'h') {
                    showPronunciation = !showPronunciation;
                } else if (command == 'm') {
                    showScreen = false;
                    return;
                } else if (command == 'q') {
                    Start.setShowScreen(false);
                    return;
                }

                if (showPronunciation) {
                    CantoPAndT cantoFinder = new CantoPAndT(Start.getPathToCantoPAndT());
                    MPAndT mFinder = new MPAndT(Start.getPathToMPAndT());

                    drawPronunciation(cantoFinder.characterCombo(this.chineseChar), traditionalSide);
                    drawPronunciation(mFinder.characterCombo(this.chineseChar), simplifiedSide);
                }

                this.drawBotScreen();

                StdDraw.show();
            }
        }

    /**
     * Put the pronunciation combos of a character to the screen.
     * @param pronunciations    List of pronunciation combos from the Character.
     * @param xCoord    The coordinate that the character is drawn to. Use to find relative position
     *                  to place the pronunciations.
     */
    private void drawPronunciation(List<String[]> pronunciations, double xCoord) {

        StdDraw.setFont(Utils.createChineseFont(20));

        double yStart = 0.6;
        int ySubtract = 0;

        int count = 1;
        for (String[] pronunciation : pronunciations) {
            StdDraw.text(xCoord, yStart - (0.05 * ySubtract),
                    pronunciation[0] + pronunciation[1] + "   " + count);
            ySubtract = ySubtract + 1;
            count = count + 1;
        }

    }

    /**
     * Draws the part of the screen above the pronunciations.
     * @param simplifiedSide    double, determines where to put the simplified character.
     * @param traditionalSide   double, determines where to put the traditional character.
     * @param characterDrawY    double, where vertically to draw both characters
     */
    private void drawTopScreen(double simplifiedSide, double traditionalSide, double characterDrawY) {
        StdDraw.clear(Color.ORANGE);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(Utils.createChineseFont(50));

        StdDraw.text(simplifiedSide, characterDrawY, chineseChar.getSimplified());
        StdDraw.text(traditionalSide, characterDrawY, chineseChar.getTraditional());
    }

    /**
     * Draw the part of the screen below the pronunciations.
     */
    private void drawBotScreen() {
        Font englishFont = Utils.comicSansFont(20);
        StdDraw.setFont(englishFont);
        StdDraw.text(0.2, 0.4, "Examples");

        StdDraw.setFont(Utils.createChineseFont(20));
        int i = 0;
        for (String example : chineseChar.getExampleUses()) {
            StdDraw.text(0.2, 0.3 - (0.05 * i), example);
            i = i + 1;
        }
    }

}
