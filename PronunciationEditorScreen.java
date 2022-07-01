
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** Screen with edit pronunciation options for tone character or pronunciation character mappings. **/
public class PronunciationEditorScreen implements DictionaryScreen {

    private void mainMenu() {
        StdDraw.clear(Color.orange);
        StdDraw.setFont(Utils.comicSansFont(20));
        StdDraw.text(0.5, 0.5, "Edit Tone Mapping (T)");
        StdDraw.text(0.5, 0.4, "Edit Pronunciation Mapping (P)");
        StdDraw.text(0.5, 0.3, "Main Menu (M)");
    }

    @Override
    public void drawToScreen() {
        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                if (StdDraw.nextKeyTyped() == 't') {
                    // tone editor GUI
                } else if (StdDraw.nextKeyTyped() == 'p') {
                    // pronunciation editor GUI
                } else if (StdDraw.nextKeyTyped() == 'm') {
                    // back to main menu
                }
            }

        }
    }


}
