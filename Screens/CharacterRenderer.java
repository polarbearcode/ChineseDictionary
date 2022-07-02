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

    private final String cantoFinderMapPath = "./testCPAndT.srl";

    private final String mFinderMapPath = "./testMPAndT.srl";

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

        while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    char command = StdDraw.nextKeyTyped();
                    if (command == 'h') {
                        showPronunciation = !showPronunciation;
                    } else if (command == 'm') {
                        Start.mainMenu();
                        showScreen = false;
                        return;
                    } else if (command == 'q') {
                        Start.setShowScreen(false);
                        return;
                    }
                }

                StdDraw.clear(Color.ORANGE);

                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setFont(Utils.createChineseFont(50));

                StdDraw.text(simplifiedSide, characterDrawY, chineseChar.getSimplified());
                StdDraw.text(traditionalSide, characterDrawY, chineseChar.getTraditional());

                if (showPronunciation) {
                    CantoPAndT cantoFinder = new CantoPAndT(this.cantoFinderMapPath);
                    MPAndT mFinder = new MPAndT(this.mFinderMapPath);

                    drawPronunciation(cantoFinder.characterCombo(this.chineseChar), traditionalSide);
                    drawPronunciation(mFinder.characterCombo(this.chineseChar), simplifiedSide);
                }

                Font englishFont = Utils.comicSansFont(20);
                StdDraw.setFont(englishFont);
                StdDraw.text(0.2, 0.4, "Examples");

                StdDraw.setFont(Utils.createChineseFont(20));
                int i = 0;
                for (String example : chineseChar.getExampleUses()) {
                    StdDraw.text(0.2, 0.3 - (0.05 * i), example);
                    i = i + 1;
                }

                StdDraw.show();

                while (!StdDraw.hasNextKeyTyped()) {
                    continue;
                }
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
        for (String[] pronunciation : pronunciations) {
            StdDraw.text(xCoord, yStart - (0.05 * ySubtract),
                    pronunciation[0] + pronunciation[1]);
            ySubtract = ySubtract + 1;
        }

    }

    }
