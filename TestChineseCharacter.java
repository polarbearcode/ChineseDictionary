import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TestChineseCharacter {

    private final String testDictionaryPath = "./testDictionary.srl";

    private final String testMapPath = "./testCPAndT.srl";
    private final CharacterList c = new CharacterList(testDictionaryPath);
    private final ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
            "hon3", "han4");

    @Test
    public void testAlreadyAdded() {

        c.addCharacter(汉);

        int curSize = c.size();

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon3", "han4");

        c.addCharacter(copy);

        assertEquals(curSize, c.size());

        c.removeChar("汉");


        assertEquals(0, c.size());

        cleanUpDictionary();
    }

    @Test
    public void testUpdateChar() {

        c.addCharacter(汉);

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon4", "han4");

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

        cleanUpDictionary();
    }

    @Test
    public void testAddExample() {
        cleanUpDictionary();
        c.addCharacter(汉);
        c.addExample(汉,"汉字");
        c.addExample(汉,"蚶子");

        Set<String> actual = c.lookUp(汉.getSimplified()).getExampleUses();
        Set<String> expected = new HashSet<String>();
        expected.add("汉字");

        assertEquals(expected, actual);
    }

    @Test
    public void testCantoneseCombo() {
        String[] firstCombo = new String[]{"漢", "酱"};
        CantoPAndT finder = new CantoPAndT(testMapPath);
        assertArrayEquals(firstCombo, finder.characterCombo(汉).get(0));

        String[] secondCombo = new String[]{"漢", "番"};

        ChineseCharacter sameSound = new ChineseCharacter("刊", "刊", "hon1",
                "kan4");
        assertArrayEquals(secondCombo, finder.characterCombo(sameSound).get(0));

        ChineseCharacter newChar = new ChineseCharacter("用", "用",
                "jung6", "yong4");

        String[] thirdCombo = new String[]{"用", "面"};

        assertArrayEquals(thirdCombo, finder.characterCombo(newChar).get(0));

        cleanUpMapping(finder);
    }

    @Test
    public void testChangeCharacterMapping() {
        CantoPAndT finder = new CantoPAndT(testMapPath);
        finder.characterCombo(汉);

        finder.changeCharacterMapping("hon3", "看");

        String[] expected = new String[]{"看", "酱"};

        assertArrayEquals(expected, finder.characterCombo(汉).get(0));
    }

    @Test
    public void testMultiplePronunciations() {
        
    }


    /** Clean up the dictionary after testing. **/
    private void cleanUpDictionary() {
        for (String chineseChar : c.getCurrentDictionary().keySet()) {
            c.removeChar(chineseChar);
        }
    }

    /** Clean up the pronunciation mapping after testing. **/
    private void cleanUpMapping(CantoPAndT finder) {
        for (String p : finder.getPronunCharacters().keySet()) {
            finder.deleteCharacterMapping(p);
        }
    }
}
