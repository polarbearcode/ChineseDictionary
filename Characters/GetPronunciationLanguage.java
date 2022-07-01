package Characters;

import java.util.Set;


/** A function to get the pronunciation of a character in either Cantonese or Mandarin. **/
public interface GetPronunciationLanguage {

    /** Get the pronunciation of a character. **/
    Set<String> getPronunciation(ChineseCharacter chineseCharacter);
}
