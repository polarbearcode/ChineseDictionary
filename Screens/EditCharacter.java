package Screens;

import Characters.CharacterList;
import ButtonListeners.*;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionListener;



/** Edit character information in the dictionary. **/
public class EditCharacter extends LookUpCharacter implements DictionaryScreen{

    private CharacterInput charInputScreen;

    private JFrame mainFrame;

    public EditCharacter() {
        super(new CharacterList(Start.getPathToDictionary()));

        super.drawToScreen();

        mainFrame = this.getMainFrame();

        JButton removeButton = this.getLookUpButton();

        mainFrame.setVisible(false);


        removeButton.setText("Remove Char");
        removeButton.setName("Remove Char");
        this.removeListeners(removeButton);

        Utils.setJComponentBounds(removeButton, 0.1, 0.5, 0.35, 0.1,
                mainFrame.getWidth(), mainFrame.getHeight());
        removeButton.addActionListener(new RemoveButtonListener(this));
        this.addToMainFrame(removeButton);

        JButton editButton = new JButton("Edit");
        editButton.setName("Edit");

        EditCharButtonListener editListener = new EditCharButtonListener(this);
        editButton.addActionListener(editListener);

        Utils.setJComponentBounds(editButton, 0.5, 0.5, 0.3, 0.1,
                mainFrame.getWidth(), mainFrame.getHeight());
        this.addToMainFrame(editButton);
    }

    @Override
    public void drawToScreen() {

        this.mainFrame.setVisible(true);
    }

    /**
     * Remove all listeners on the button.
     * @param button The button to remove listeners from.
     */
    private void removeListeners(JButton button) {

        for (ActionListener a : button.getActionListeners()) {
            button.removeActionListener(a);
        }
    }


}
