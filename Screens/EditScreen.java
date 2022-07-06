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

       boolean showScreen = true;


       while(showScreen) {

           this.editMenu();

           char command = Utils.getNextCommand();

               if (command == 'e') {
                    EditCharacter editChar = new EditCharacter();
                    editChar.drawToScreen();
               } else if (command == 'p') {
                    PronunciationEditorScreen p = new PronunciationEditorScreen();
                    p.drawToScreen();
               } else if (command == 'm') {
                   showScreen = false;
               }
           }
       }
}
