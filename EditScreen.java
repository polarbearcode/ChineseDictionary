import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

/** Screen to edit character information or edit the pronunciation and tone mappings. **/
public class EditScreen {

    public static void editMenu() {
        StdDraw.clear(Color.orange);
        StdDraw.setFont(Utils.comicSansFont(30));
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.text(0.5, 0.7, "Edit Dictionary (E)");
        StdDraw.text(0.5, 0.6, "Edit Pronunciations (P)");
        StdDraw.text(0.5, 0.5, "Return to Menu (M)");
    }

    public static void drawToScreen() {
       editMenu();

       while(true) {
           if (StdDraw.hasNextKeyTyped()) {
               char command = StdDraw.nextKeyTyped();
               if (command == 'e') {

               } else if (command == 'p') {

               } else if (command == 'm') {
                   Start.mainMenu();
                   return;
               }
           }
       }
    }
}
