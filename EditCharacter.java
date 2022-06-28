import javax.swing.*;

/** Edit character information in the dictionary. **/
public class EditCharacter extends LookUpCharacter{

    public EditCharacter() {
        super(new CharacterList(Start.getPathToDictionary()));

        JFrame mainFrame = this.getMainFrame();
        JButton removeButton = this.getLookUpButton();


        removeButton.setText("Remove Char");
        removeButton.setBounds((int)(mainFrame.getWidth() * 0.1), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.35), (int)(mainFrame.getHeight() * 0.1));
        this.addToMainFrame(removeButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds((int)(mainFrame.getWidth() * 0.5), (int)(mainFrame.getHeight() * 0.5),
                (int)(mainFrame.getWidth() * 0.3), (int)(mainFrame.getHeight() * 0.1));
        this.addToMainFrame(editButton);

    }



}
