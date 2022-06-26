import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/** Main entry point to the program. **/
public class Start {

    /** Path to the dictionary file. **/
    private static String pathToDictionary = "./testDictionary.srl";

    private static boolean showScreen = true;
    /** Draw the main menu screen. **/
    static void mainMenu() {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.clear(Color.orange);
        Font mainMenuFont = new Font("Comic Sans MS", Font.PLAIN, 30);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.setFont(mainMenuFont);
        StdDraw.text(0.5, 0.9, "Chinese Dictionary");

        StdDraw.setFont(Utils.createChineseFont(50));
        StdDraw.text(0.5, 0.8, "字典");

        Font optionsFont = new Font("Comic Sans MS", Font.PLAIN, 17);
        StdDraw.setFont(optionsFont);
        StdDraw.text(0.5, 0.6, "Look Up (L)");
        StdDraw.text(0.5, 0.5, "Add Character (A)");
        StdDraw.text(0.5, 0.4, "Random Character (X)");
        StdDraw.text(0.5, 0.3, "Exit (Q)");

    }

    /** Set show screen.
     * @param   trueOrFalse Set to true to keep screen shown, false to close screen.
     */
    public static void setShowScreen(boolean trueOrFalse) {
        showScreen = trueOrFalse;
    }

    private static char getNextCommand() {
        while (!StdDraw.hasNextKeyTyped()) {
            continue;
        }

        return StdDraw.nextKeyTyped();
    }



    public static void main(String[] args) {

        CharacterList charList = new CharacterList(pathToDictionary);

        mainMenu();

        while(showScreen) {

            char command = getNextCommand();

            if (command == 'l') {
                LookUpCharacter lookUpChar = new LookUpCharacter(charList);

                while (lookUpChar.getNextScreen() == null) {
                    continue;
                }

                lookUpChar.getNextScreen().drawToScreen();

            } else if (command == 'a') {
                CharacterInput characterInput = new CharacterInput(charList);
            } else if (command == 'x') {
                Set<String> dictionaryChars = charList.getCurrentDictionary().keySet();
                List<String> characterKeys = new ArrayList<>();
                characterKeys.addAll(dictionaryChars);
                Collections.shuffle(characterKeys);

                CharacterRenderer c = new CharacterRenderer(charList.lookUp(characterKeys.get(0)));
                c.drawToScreen();

            } else if (command == 'q') {
                break;
            }
        }

        System.exit(0);
    }
}

