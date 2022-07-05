package Screens;

import Characters.CantoPAndT;
import Characters.GetCantonesePronunciation;
import Characters.MPAndT;
import Characters.PronunciationAndTone;
import Utils.*;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.regex.Pattern;

/** Screen with edit pronunciation options for tone character or pronunciation character mappings. **/
public class PronunciationEditorScreen implements DictionaryScreen {

    private void mainMenu() {
        StdDraw.clear(Color.orange);
        StdDraw.setFont(Utils.comicSansFont(20));
        StdDraw.text(0.5, 0.5, "Edit Pronunciation Mapping (C)");
        StdDraw.text(0.5, 0.4, "Edit Pronunciation Mapping (M)");
        StdDraw.text(0.5, 0.3, "Main Menu (B)");
    }

    @Override
    public void drawToScreen() {
        mainMenu();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char command = StdDraw.nextKeyTyped();
                PronunciationAndTone pAndT;
                PronunciationMappingEditor p;
                if (command == 'c') {
                    pAndT = new CantoPAndT(Start.getPathToCantoPAndT());
                    p = new PronunciationMappingEditor(pAndT);
                    p.drawToScreen();
                } else if (command == 'm') {
                    pAndT = new MPAndT(Start.getPathToMPAndT());
                    p = new PronunciationMappingEditor(pAndT);
                    p.drawToScreen();
                } else if (command == 'b') {
                    return;
                }
            }

        }
    }


}
