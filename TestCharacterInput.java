import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;

public class TestCharacterInput {
    private FrameFixture window;

    @Before
    public void setUp() {
        CharacterList testCharList = new CharacterList("./testdictionary.srl");
        CharacterInput c = GuiActionRunner.execute(() -> new CharacterInput(500, 500, testCharList));
        window = new FrameFixture(c.getMainFrame());
        window.show();
    }

    @Test
    public void testCharacterInput() {

        window.textBox("Simplified Character").enterText("种");
        window.textBox("Traditional Character").enterText("種");
        window.textBox("Pinyin").enterText("zhong3");
        window.textBox("Cantonese Pronunciations").enterText("zung2, zung3");
        window.textBox("Examples").enterText("种植,种群");
        window.button("Add Button").click();

    }
}
