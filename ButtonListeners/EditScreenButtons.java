package ButtonListeners;
import Screens.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipalLookupService;

/** Base class for the remove char and edit char action listeners. **/
public abstract class EditScreenButtons implements ActionListener {

    LookUpCharacter editScreen;

    EditScreenButtons(LookUpCharacter editScreen) {
        this.editScreen = editScreen;
    }

    /**
     * Check the character input is valid and is in the dictionary.
     * @param input The character entered into the text box.
     * @return  True if the input is 1 Chinese character in the dictionary. False otherwise.
     */
    public boolean checkInput(String input) {
        return input.length() == 1 && editScreen.getCharList().lookUp(input) != null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String input = editScreen.getCharBox().getText();

        if (checkInput(input)) {
           action();
        } else {
            editScreen.getErrorLabel().setText("Fix input");
            editScreen.getCharBox().setBackground(Color.red);
        }
    }

    public abstract void action();
}

