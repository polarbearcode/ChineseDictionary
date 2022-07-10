package Screens;

import Characters.CharacterList;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import Utils.*;

/** Main entry point to the program. **/
public class Start {

    /** Path to the dictionary file. **/
    private static String pathToDictionary = "Test/testDictionary.srl";

    private static String pathToCantoPAndT = "Test/testCPAndT.srl";

    private static String pathToMPAndT = "Test/testMPAndT.srl";

    private static boolean showScreen = true;
    /** Draw the main menu screen. **/
    static void mainMenu() {
        StdDraw.clear(Color.orange);
        StdDraw.setCanvasSize(500, 500);
        StdDraw.clear(Color.orange);
        Font mainMenuFont = Utils.comicSansFont(30);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.setFont(mainMenuFont);
        StdDraw.text(0.5, 0.9, "Chinese Dictionary");

        StdDraw.setFont(Utils.createChineseFont(50));
        StdDraw.text(0.5, 0.8, "字典");

        Font optionsFont = Utils.comicSansFont(17);
        StdDraw.setFont(optionsFont);
        StdDraw.text(0.5, 0.6, "Look Up (L)");
        StdDraw.text(0.5, 0.5, "Add Character (A)");
        StdDraw.text(0.5, 0.4, "Random Character (X)");
        StdDraw.text(0.5, 0.3, "Flash Cards (F)");
        StdDraw.text(0.5, 0.2, "Edit (E)");
        StdDraw.text(0.5, 0.1, "Exit (Q)");

    }

    /** Set show screen.
     * @param   trueOrFalse Set to true to keep screen shown, false to close screen.
     */
    public static void setShowScreen(boolean trueOrFalse) {
        showScreen = trueOrFalse;
    }

    /**Get the path to the dictionary program is tied to. **/
    public static String getPathToDictionary() {
        return pathToDictionary;
    }

    /** Get path to Cantonese mapping file. **/
    public static String getPathToCantoPAndT() {
        return pathToCantoPAndT;
    }

    /** Get path to Mandarin mapping file. **/
    public static String getPathToMPAndT() {
        return pathToMPAndT;
    }

    /** Set the path to the dictionaryu program is tied to.
     * @param pathToDictionary  String, path to the dictionary file.
     */
    public static void setPathToDictionary(String pathToDictionary) {
        Start.pathToDictionary = pathToDictionary;
    }

    public void drawToScreen() {
        CharacterList charList = new CharacterList(pathToDictionary);

        while(showScreen) {

            mainMenu();

            char command = Utils.getNextCommand();

            if (command == 'l') {
                LookUpCharacter lookUpChar = new LookUpCharacter(charList);
                lookUpChar.drawToScreen();

                while (lookUpChar.getNextScreen() == null && lookUpChar.getShowScreen()) {
                    continue;
                }

                if (!lookUpChar.getShowScreen()) {
                    lookUpChar.getMainFrame().dispose();
                } else if (lookUpChar.getNextScreen() != null) {
                    lookUpChar.getNextScreen().drawToScreen();
                }

            } else if (command == 'a') {
                CharacterInput characterInput = new CharacterInput(charList);
                characterInput.drawToScreen();
            } else if (command == 'x') {
                try {
                    Set<String> dictionaryChars = charList.getCurrentDictionary().keySet();
                    List<String> characterKeys = new ArrayList<>();
                    characterKeys.addAll(dictionaryChars);
                    Collections.shuffle(characterKeys);

                    CharacterRenderer c = new CharacterRenderer(charList.lookUp(characterKeys.get(0)));
                    c.drawToScreen();
                } catch (IndexOutOfBoundsException e) {
                    StdDraw.setFont(Utils.comicSansFont(10));
                    StdDraw.text(0.1, 0.5, "No characters in dictionary");
                }

            } else if (command == 'q') {
                break;
            } else if (command == 'e') {
                EditScreen e = new EditScreen();
                e.drawToScreen();
            }
            charList = new CharacterList(Start.pathToDictionary);
        }

    }

    public static void main(String[] args) {

        Start s = new Start();
        s.drawToScreen();
        System.exit(0);
    }
}

