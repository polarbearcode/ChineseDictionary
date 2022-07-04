package Test;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Characters.*;
import Screens.*;

public class TestEditProMap {

    private FrameFixture window;

    @Before
    public void setUp() {

        CantoPAndT cantoPAndT = new CantoPAndT(Start.getPathToCantoPAndT());

        PronunciationMappingEditor pME = GuiActionRunner.execute(() -> new PronunciationMappingEditor(cantoPAndT));

        window = new FrameFixture(pME.getMainFrame());
        window.show();
        window.maximize();
    }
}
