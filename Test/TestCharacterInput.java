package Test;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import Characters.*;
import Screens.*;

import static org.junit.Assert.assertEquals;

public class TestCharacterInput {
    private FrameFixture window;

    private final CharacterList testCharList = new CharacterList("./testDictionary.srl");

    @Before
    public void setUp() {
        CharacterInput c = GuiActionRunner.execute(() -> new CharacterInput(testCharList));

        testCharList.clearDictionary();

        c.drawToScreen();

        window = new FrameFixture(c.getMainFrame());
        window.show();
        window.maximize();
    }

    @Test
    public void testCharacterInput() {

        window.textBox("Simplified Character").enterText("种");
        window.textBox("Traditional Character").enterText("種");
        window.textBox("Pinyin").enterText("zhong3, zhong");
        window.textBox("Cantonese Pronunciations").enterText("zung2, zung3");
        window.textBox("Examples").enterText("种植,种群,你好");
        window.button("Add Character").click();

        Set<String> expectedCPronunciation = new HashSet<>();
        expectedCPronunciation.add("zung2");
        expectedCPronunciation.add("zung3");

        Set<String> expectedPinyin = new HashSet<>();
        expectedPinyin.add("zhong3");
        expectedPinyin.add("zhong");

        Set<String> expectedExamples = new HashSet<>();
        expectedExamples.add("种植");
        expectedExamples.add("种群");

        ChineseCharacter addedChar = testCharList.lookUp("种");

        assertEquals("种", addedChar.getSimplified());
        assertEquals("種", addedChar.getTraditional());
        assertEquals(expectedCPronunciation, addedChar.getCantonesePronunciation());
        assertEquals(expectedPinyin, addedChar.getMandarinPronunciation());
        assertEquals(expectedExamples, addedChar.getExampleUses());

    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
