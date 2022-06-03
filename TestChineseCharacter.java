import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class TestChineseCharacter {

    private final String testDictionaryPath = "./testDictionary.srl";
    private final CharacterList c = new CharacterList(testDictionaryPath);

    @Test
    public void testAlreadyAdded() {

        ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

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
        ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        c.addCharacter(汉);

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon4");

        c.addCharacter(copy);

        assertEquals(1, c.size());

        assertEquals(2, 汉.getCantonesePronunciation().size());

        cleanUpDictionary();
    }


    /** Clean up the dictionary after testing. **/
    private void cleanUpDictionary() {
        for (String chineseChar : c.getCurrentDictionary().keySet()) {
            c.removeChar(chineseChar);
        }
    }
}
