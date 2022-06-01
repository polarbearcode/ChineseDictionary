import java.util.List;

/** Represents a Chinese character with simplified and traditional character, audio file,
 * and an example sentence/pairs.
 */
public class ChineseCharacter {

    /** String for simplified character. **/
    private String simplified;

    /**String for the traditional character. **/
    private String traditional;

    /** String for the Cantonese pronunciation of the character. **/
    private String cantonesePronunciation;

    /** String for the pinyin pronunciation of the character. **/
    private String pinyin;

    /** Path to the character's audio in the Audio folder. **/
    private String audioFile;

    /** Sentences and/or phrases using the character. **/
    List<String> exampleUses;


    /**
     * Create a Chinese character.
     * @param simplified String for simplified character.
     * @param traditional String for traditional character.
     * @param cp    String for the cantonese pronunciation.
     * @param py    String for the pinyin.
     * @param audio A String representing the path to the audio file in the Audio folder.
     */
    public ChineseCharacter(String simplified, String traditional, String cp, String py, String audio) {

    }
}
