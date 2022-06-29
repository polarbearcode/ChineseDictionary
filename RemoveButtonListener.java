import java.awt.event.ActionListener;

/** Listener for the Remove Button on the edit character screen. **/
public class RemoveButtonListener extends EditScreenButtons implements ActionListener {

    /**
     * Tie button to the EditCharacter screen.
     * @param editScreen    The EditCharacter screen to get the character input.
     */
    RemoveButtonListener(LookUpCharacter editScreen) {
        super(editScreen);
    }

    /**
     * Remove the character from the CharacterList.
     */
    @Override
    public void action() {
        this.editScreen.getCharList().removeChar(this.editScreen.getCharBox().getText());
    }
}
