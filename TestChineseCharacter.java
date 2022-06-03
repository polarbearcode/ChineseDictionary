import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class TestChineseCharacter {

    private final String testDictionaryPath = "./testDictionary.srl";
    private final CharacterList c = new CharacterList(testDictionaryPath);
    private final ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
            "hon3", "han4", "Audio/hon3");

    @Test
    public void testAlreadyAdded() {

        c.addCharacter(汉);

        int curSize = c.size();

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        c.addCharacter(copy);

        assertEquals(curSize, c.size());

        c.removeChar("汉");


        assertEquals(0, c.size());

        cleanUpDictionary();

        int x = 10;
    }

    @Test
    public void testUpdateChar() {

        c.addCharacter(汉);

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon4", "han4", "Audio/hon4");

        c.addCharacter(copy);

        assertEquals(1, c.size());

        assertEquals(2, c.lookUp("汉").getCantonesePronunciation().size());

        cleanUpDictionary();
    }

    @Test
    public void testAddPronunciation() {
        c.addCharacter(汉);
        c.addCantonesePronunciation(汉, "hon1","hon1.mp3");
        c.addCantonesePronunciation(汉, "hon1", "pin1.mp3");

        assertEquals(2, c.lookUp(汉.getSimplified()).getCantonesePronunciation().size());


    }


    /** Clean up the dictionary after testing. **/
    private void cleanUpDictionary() {
        for (String chineseChar : c.getCurrentDictionary().keySet()) {
            c.removeChar(chineseChar);
        }
    }
}
