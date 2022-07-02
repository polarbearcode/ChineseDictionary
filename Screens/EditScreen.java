package Screens;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import Utils.*;

/** Screen to edit character information or edit the pronunciation and tone mappings. **/
public class EditScreen implements DictionaryScreen {

    public void editMenu() {
        StdDraw.clear(Color.orange);
        StdDraw.setFont(Utils.comicSansFont(30));
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.text(0.5, 0.7, "Edit Character Info (E)");
        StdDraw.text(0.5, 0.6, "Edit Pronunciations (P)");
        StdDraw.text(0.5, 0.5, "Return to Menu (M)");
    }

    @Override
    public void drawToScreen() {
       this.editMenu();

       boolean showScreen = true;

       while(showScreen) {
           if (StdDraw.hasNextKeyTyped()) {
               char command = StdDraw.nextKeyTyped();
               if (command == 'e') {
                    EditCharacter editChar = new EditCharacter();
               } else if (command == 'p') {

               } else if (command == 'm') {
                   showScreen = false;
               }
           }
       }
    }
}
