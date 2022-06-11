import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterInput {


    public static void main(String[] args) {



        JFrame mainFrame = new JFrame("Character Input");
        mainFrame.setSize(400,400);

        mainFrame.getContentPane().setBackground(Color.YELLOW);

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 16);

        JLabel simplifiedChar = new JLabel("Simplified Character");
        simplifiedChar.setFont(labelFont);
        simplifiedChar.setBounds(50, 30, 250, 20);

        JTextArea simplifiedCharacterText = new JTextArea("");
        simplifiedCharacterText.setBounds(250, 30, 100, 20);
        simplifiedCharacterText.setBackground(Color.cyan);

        JLabel tradChar = new JLabel("Traditional Character");
        tradChar.setFont(labelFont);
        tradChar.setBounds(50, 60, 250, 20);

        JTextArea tradCharText = new JTextArea("");
        tradCharText.setBounds(250, 60, 100, 20);
        tradCharText.setBackground(Color.cyan);


        mainFrame.add(simplifiedCharacterText);
        mainFrame.add(simplifiedChar);
        mainFrame.add(tradChar);
        mainFrame.add(tradCharText);

        mainFrame.setLayout(null);

        mainFrame.setVisible(true);



    }

}

