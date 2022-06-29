import java.awt.event.ActionListener;

public class RemoveButtonListener extends EditScreenButtons implements ActionListener {

    RemoveButtonListener(LookUpCharacter editScreen) {
        super(editScreen);
    }
    @Override
    public void action() {
        this.editScreen.getCharList().removeChar(this.editScreen.getCharBox().getText());
    }
}
