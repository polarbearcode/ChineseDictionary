package ButtonListeners;

import Screens.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Listener to use for look up character button. **/
public class LookUpButtonListener implements ActionListener {

    private LookUpCharacter lookUpScreen;

    public LookUpButtonListener(LookUpCharacter l) {
        super();
        lookUpScreen = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Look Up")) {
            lookUpScreen.getCharBox().setBackground(Color.WHITE);
            lookUpScreen.getErrorLabel().setText("");
            String input = lookUpScreen.getCharBox().getText();
            while (!lookUpScreen.checkCharacterInputValid(input)) {
                lookUpScreen.getErrorLabel().setText("Input must be 1 Chinese character");
                lookUpScreen.getCharBox().setBackground(Color.RED);
                return;
            }

            if (lookUpScreen.getCharList().lookUp(input) != null) {
                ChineseCharacter c = lookUpScreen.getCharList().lookUp(input);
                lookUpScreen.setNextScreen(new CharacterRenderer(c));
            } else {
                lookUpScreen.setNextScreen(new CharacterInput(lookUpScreen.getCharList()));
            }

            lookUpScreen.hidMainFrame();
        }
    }

}
