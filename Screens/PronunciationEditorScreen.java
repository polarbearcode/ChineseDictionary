package Screens;

import Utils.*;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

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
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                if (StdDraw.nextKeyTyped() == 'c') {
                    // cantonese
                } else if (StdDraw.nextKeyTyped() == 'm') {
                    // pinyin
                } else if (StdDraw.nextKeyTyped() == 'b') {
                    // back to main menu
                }
            }

        }
    }


}
