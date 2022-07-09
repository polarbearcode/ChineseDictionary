package ButtonListeners;

import Screens.*;
import java.awt.event.ActionListener;

/** Listener for the Remove Button on the edit character screen. **/
public class RemoveButtonListener extends EditScreenButtons implements ActionListener {

    /**
     * Tie button to the EditCharacter screen.
     * @param editScreen    The EditCharacter screen to get the character input.
     */
    public RemoveButtonListener(LookUpCharacter editScreen) {
        super(editScreen);
    }

    /**
     * Remove the character from the Characters.CharacterList.
     */
    @Override
    public void action() {
        this.editScreen.getCharList().removeChar(this.editScreen.getCharBox().getText());
        this.editScreen.getMainFrame().dispose();
    }
}
