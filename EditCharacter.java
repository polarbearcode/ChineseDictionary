import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Edit character information in the dictionary. **/
public class EditCharacter extends LookUpCharacter{

    public EditCharacter() {
        super(new CharacterList(Start.getPathToDictionary()));

        JFrame mainFrame = this.getMainFrame();
        JButton removeButton = this.getLookUpButton();


        removeButton.setText("Remove Char");
        removeButton.setName("Remove Char");
        this.removeListeners(removeButton);

        removeButton.setBounds((int)(mainFrame.getWidth() * 0.1), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.35), (int)(mainFrame.getHeight() * 0.1));
        removeButton.addActionListener(new RemoveButtonListener(this));
        this.addToMainFrame(removeButton);

        JButton editButton = new JButton("Edit");
        editButton.setName("Edit");
        editButton.addActionListener(new EditCharButtonListener(this));
        editButton.setBounds((int)(mainFrame.getWidth() * 0.5), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.3), (int)(mainFrame.getHeight() * 0.1));
        this.addToMainFrame(editButton);
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
