import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class TestChineseCharacter {

    @Test
    public void testAlreadyAdded() {

        ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        CharacterList.updateDictionaryFile();

        CharacterList.addCharacter(汉);

        Set<ChineseCharacter> characterSet = CharacterList.getDictionary();

        int curSize = characterSet.size();

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        CharacterList.addCharacter(copy);

        characterSet =CharacterList.getDictionary();

        assertEquals(curSize, characterSet.size());

        CharacterList.removeChar(汉);

        characterSet = CharacterList.getDictionary();

        assertEquals(0, characterSet.size());
    }

    @Test
    public void testUpdateChar() {
        ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");
        CharacterList.addCharacter(汉);

    }


    /** Clean up the dictionary after testing. **/
    private void cleanUpDictionary() {
        CharacterList.updateDictionaryFile();
        
    }
}
