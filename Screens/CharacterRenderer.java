package Screens;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.*;

import Characters.*;
import Utils.*;


/** Put the character information onto the window. **/
public class CharacterRenderer implements DictionaryScreen {

    /** The character whose information will be shown. **/
    private final ChineseCharacter chineseChar;

    /** The character list the information is tied to. **/
    private final CharacterList charList;

    private final int windowWidth = 500;

    private final int windowHeight = 300;


    private boolean showScreen = true;

    private boolean showPronunciation = false;

    private Map<Character, String> pronunciationCommands;

    private final CantoPAndT cantoPAndT = new CantoPAndT(Start.getPathToCantoPAndT());




    public CharacterRenderer(ChineseCharacter chineseChar) {
        this.chineseChar = chineseChar;
        this.pronunciationCommands = new HashMap<>();
        this.charList = new CharacterList(Start.getPathToDictionary());
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
                } else if (showPronunciation && this.pronunciationCommands.containsKey(command)) {
                        this.playFile(command);
                }

                if (showPronunciation) {
                    CantoPAndT cantoFinder = new CantoPAndT(Start.getPathToCantoPAndT());
                    MPAndT mFinder = new MPAndT(Start.getPathToMPAndT());

                    drawPronunciation(cantoFinder.characterCombo(this.chineseChar), traditionalSide,
                            true);
                    drawPronunciation(mFinder.characterCombo(this.chineseChar), simplifiedSide,
                            false);
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
     * @param showNumber  boolean indicating whether to add a number next to the pronunciation
     *                    to press the audio.
     */
    private void drawPronunciation(List<String[]> pronunciations, double xCoord, boolean showNumber) {

        StdDraw.setFont(Utils.createChineseFont(20));

        CantoPAndT cantoPAndT = new CantoPAndT(Start.getPathToCantoPAndT());

        double yStart = 0.6;
        int ySubtract = 0;

        int count = 1;
        for (String[] pronunciation : pronunciations) {
            String pronunciationString = pronunciation[0] + pronunciation[1];
            if (showNumber) {
                this.pronunciationCommands.put((char) count, this.findAudioFile(pronunciation));
                pronunciationString = pronunciationString + "  " + count;
            }
            StdDraw.text(xCoord, yStart - (0.05 * ySubtract),
                    pronunciationString);
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

    /**
     * Find the audio file associated with the pronunciation combo.
     * @param pronunciationCombo A length two array with the 0-index character being the
     *                           pronunciation and the 1-index character being the tone.
     * @return  A String for the file path of the corresponding audio file.
     */
    private String findAudioFile(String[] pronunciationCombo) {
        return "Audio/" + this.cantoPAndT.pronunciationFromCombo(pronunciationCombo) + ".mp3";
    }

    /**
     * Play the audio file that maps to the command in the pronunciation commands map.
     * @param command   A Char from 1 to the number of pronunciations of the character. 
     */
    private void playFile(char command) {

    }

}
