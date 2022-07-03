package ButtonListeners;

import Screens.PronunciationEditorScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PronunciationMappingEditListener implements ActionListener {

    /** The edit screen where the button as pressed. **/
    private PronunciationEditorScreen editScreen;

    public PronunciationMappingEditListener(PronunciationEditorScreen editScreen) {
        this.editScreen = editScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Edit")) {

        }
    }


}