import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** Main entry point to the program. **/
public class Start {

    /** Path to the dictionary file. **/
    private static String pathToDictionary = "./testDictionary.srl";

    private static void mainMenu() {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.clear(Color.orange);
        Font mainMenuFont = new Font("Comic Sans MS", Font.PLAIN, 30);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.setFont(mainMenuFont);
        StdDraw.text(0.5, 0.9, "Chinese Dictionary");

        StdDraw.setFont(Utils.chineseFont);
        StdDraw.text(0.5, 0.8, "字典");

        Font optionsFont = new Font("Comic Sans MS", Font.PLAIN, 17);
        StdDraw.setFont(optionsFont);
        StdDraw.text(0.5, 0.6, "Look Up (L)");
        StdDraw.text(0.5, 0.5, "Add Character (A)");

    }

    private static char getNextCommand() {
        while (!StdDraw.hasNextKeyTyped()) {
            continue;
        }

        return StdDraw.nextKeyTyped();
    }

    public static void main(String[] args) {
        mainMenu();

        while(true) {

            char command = getNextCommand();

            if (command == 'l') {
                LookUpCharacter lookUpChar = new LookUpCharacter(new CharacterList(pathToDictionary));
            }
        }

        /**
         while (true) {
         while (StdDraw.hasNextKeyTyped()) {
         //if hit the L key, then lookup screen
         //while (something)
         }
         } **/

    }
}

