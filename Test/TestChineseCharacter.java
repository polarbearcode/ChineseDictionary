package Test;

import Utils.Utils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

import Characters.*;

public class TestChineseCharacter {

    private final String testDictionaryPath = "./Test/testDictionary.srl";

    private final String testMapPath = "./Test/testCPAndT.srl";
    private final CharacterList c = new CharacterList(testDictionaryPath);
    private final ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
            "hon3", "han4");

    private final ChineseCharacter 種 = Utils.createDefaultChar();

    private final String mTestMapPath = "./Test/testMPAndT.srl";

    private CantoPAndT cantoFinder = new CantoPAndT(testMapPath);

    private MPAndT pinyinFinder = new MPAndT(mTestMapPath);



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

        cleanUpDictionary();

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

        cleanUpMapping(finder);

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

        cantoFinder.characterCombo(汉);

        cantoFinder.changeCharacterMapping("hon", "看");

        String[] expected = new String[]{"看", "酱"};

        assertArrayEquals(expected, cantoFinder.characterCombo(汉).get(0));

        cleanUpMapping(cantoFinder);
    }

    @Test
    public void testMultiplePronunciations() {


        cantoFinder.changeCharacterMapping("zung", "中");

        List<String[]> expected = new ArrayList<>();
        String[] firstCombo = new String[]{"中", "茄"};
        String[] secondCombo = new String[]{"中", "酱"};

        expected.add(firstCombo);
        expected.add(secondCombo);

        assertArrayEquals(expected.get(0), cantoFinder.characterCombo(種).get(0));
        assertArrayEquals(expected.get(1), cantoFinder.characterCombo(種).get(1));

       cleanUpDictionary();
       cleanUpMapping(cantoFinder);
    }

    @Test
    public void testPinyinCombo() {
        cleanUpDictionary();

        pinyinFinder.changeCharacterMapping("zhong", "中");

        String[] expectedFirst = new String[]{"中", "好"};
        String[] expectedSecond = new String[]{"中","快"};


        assertArrayEquals(expectedFirst, pinyinFinder.characterCombo(種).get(0));
        assertArrayEquals(expectedSecond, pinyinFinder.characterCombo(種).get(1));

    }

    @Test
    public void testNoTone() {
        ChineseCharacter noToneChar = new ChineseCharacter("的","的","dik1", "de");

        String[] expected = new String[]{"的", "的"};
        assertArrayEquals(expected, pinyinFinder.characterCombo(noToneChar).get(0));
    }
    @Test
    public void testAudioFinder() {

        Map<String, String> expected = new HashMap<>();
        expected.put("hon3", "./Audio/hon3.mp3");
        assertEquals(expected, c.findAudio(汉));

        Map<String, String> expected2 = new HashMap<>();
        expected2.put("zung2", "./Audio/zung2.mp3");
        expected2.put("zung3", "./Audio/zung3.mp3");
        assertEquals(expected2, c.findAudio(種));

        cleanUpDictionary();
    }

    @Test
    public void testAddSameCharacter() {
        cleanUpDictionary();

        ChineseCharacter chineseChar = Utils.createDefaultChar();
        c.addCharacter(chineseChar);

        chineseChar.addExamples("种类");

        c.addCharacter(chineseChar);

        ChineseCharacter charLookUp = c.lookUp("种");

        assertTrue(charLookUp.getExampleUses().contains("种类"));
        assertEquals(3, charLookUp.getExampleUses().size());

        ChineseCharacter chineseCharLessExamples = Utils.createDefaultChar();
        chineseCharLessExamples.removeExample("种植");
        chineseCharLessExamples.removeExample("种群");


        c.addCharacter(chineseCharLessExamples);
        charLookUp = c.lookUp("种");

        assertFalse(charLookUp.getExampleUses().contains("种植"));
        assertEquals(0, charLookUp.getExampleUses().size());

    }



    /** Clean up the dictionary after testing. **/
    private void cleanUpDictionary() {

        c.clearDictionary();

    }

    /** Clean up the pronunciation mapping after testing. **/
    private void cleanUpMapping(PronunciationAndTone finder) {
        finder.clearMapping();
    }

    /** Helper function to create a character with multiple pronunciations.
     * @return  A Chinese character that has a list of pronunciations.  **/




}
