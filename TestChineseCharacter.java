import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class TestChineseCharacter {

    @Test
    public void testNoAudioFile() {

    }

    @Test
    public void testAlreadyAdded() {

        Set<ChineseCharacter> characterSet = CharacterList.getDictionary();

        ChineseCharacter 汉 = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        汉.saveCharacter();

        int curSize = characterSet.size();

        ChineseCharacter copy = new ChineseCharacter("汉", "漢",
                "hon3", "han4", "Audio/hon3");

        assertEquals(curSize, characterSet.size());

        characterSet.remove(汉);

    }
}
