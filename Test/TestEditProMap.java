package Test;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Characters.*;
import Screens.*;

public class TestEditProMap {

    private FrameFixture cantoneseEditor;
    CantoPAndT cantoPAndT;


    @Before
    public void setUp() {

        cantoPAndT = new CantoPAndT(Start.getPathToCantoPAndT());

        cantoPAndT.clearMapping();

        cantoPAndT.addPronunciationMapping("zung", "种");

        PronunciationMappingEditor pME = GuiActionRunner.execute(() -> new PronunciationMappingEditor(cantoPAndT));

        pME.drawToScreen();

        cantoneseEditor = new FrameFixture(pME.getMainFrame());
        cantoneseEditor.show();
        cantoneseEditor.maximize();
    }

    @Test
    public void testCantoneseEditor() {
        cantoneseEditor.textBox("Pronunciation").setText("zung");
        cantoneseEditor.textBox("Character").setText("中");
        cantoneseEditor.button("Edit").click();

        assertEquals("中", cantoPAndT.getPronunCharacters().get("zung"));
    }

    @After
    public void tearDown() {
        cantoneseEditor.cleanUp();
    }
}
