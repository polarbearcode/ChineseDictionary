package Test;
import Utils.Utils;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Screens.*;
import Characters.*;

public class TestEditCharInfo {

    private FrameFixture window;

    private EditCharacter e;

    @Before
    public void setUp() {
        Start.setPathToDictionary("./Test/testDictionary.srl");
        e = GuiActionRunner.execute(EditCharacter::new);
        e.drawToScreen();

        window = new FrameFixture(e.getMainFrame());
        window.show();
        window.maximize();
    }

    @Test
    public void testRemoveChar() {

        window.textBox("Character Box").enterText("种");
        window.button("Remove Char").click();

        CharacterList c = new CharacterList(Start.getPathToDictionary());

        assertNull(c.lookUp("种"));

        c.addCharacter(Utils.createDefaultChar());

    }


    @After
    public void tearDown() {
        window.cleanUp();
    }
}
