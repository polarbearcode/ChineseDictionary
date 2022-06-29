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
        removeButton.setBounds((int)(mainFrame.getWidth() * 0.1), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.35), (int)(mainFrame.getHeight() * 0.1));
        removeButton.addActionListener(new RemoveButtonListener());
        this.addToMainFrame(removeButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds((int)(mainFrame.getWidth() * 0.5), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.3), (int)(mainFrame.getHeight() * 0.1));
        this.addToMainFrame(editButton);
    }

    /**
     * Check the character input is valid and is in the dictionary.
     * @param input The character entered into the text box.
     * @return  True if the input is 1 Chinese character in the dictionary. False otherwise.
     */
    public boolean checkInput(String input) {
        return input.length() == 1 && getCharList().lookUp(input) != null;
    }


    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = getCharBox().getText();

            if (checkInput(input)) {
                getCharList().removeChar(input);
            } else {
                getErrorLabel().setText("Fix input");
                getCharBox().setBackground(Color.red);
            }
        }
    }





}
