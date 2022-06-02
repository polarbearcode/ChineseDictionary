import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Represents a Chinese character with simplified and traditional character, audio file,
 * and an example sentence/pairs.
 */
public class ChineseCharacter {

    /** String for simplified character. **/
    private String simplified;

    /**String for the traditional character. **/
    private String traditional;

    /** Strings for the Cantonese pronunciation of the character. **/
    private Set<String> cantonesePronunciation;

    /** Strings for the pinyin pronunciation of the character. **/
    private Set<String> pinyin;

    /** Paths to the character's audio in the Audio folder. **/
    private Set<String> audioFile;

    /** Sentences and/or phrases using the character. **/
    private Set<String> exampleUses;

    /** Pronunciation using other Chinese characters. **/
    private PronunciationAndTone pronunciationWithCharacters;


    /**
     * Create a Chinese character.
     * @param simplified String for simplified character.
     * @param traditional String for traditional character.
     * @param cp    String for the cantonese pronunciation.
     * @param py    String for the pinyin.
     * @param audio A String representing the path to the audio file in the Audio folder.
     */
    public ChineseCharacter(String simplified, String traditional, String cp, String py, String audio) {

        if (checkIfAlreadyAdded(simplified)) {
            // read in and update;
        } else {

            this.simplified = simplified;
            this.traditional = traditional;

            this.cantonesePronunciation = new HashSet<>();
            this.pinyin = new HashSet<>();
            this.audioFile = new HashSet<>();

            this.cantonesePronunciation.add(cp);
            this.pinyin.add(py);
            this.audioFile.add(audio);
        }
    }

    /**
     * Checks if the provided character is already in the dictionary.
     * @param chineseChar The character to check.
     * @return  True if the character is already in the dictionary, false otherwise.
     */
    private boolean checkIfAlreadyAdded(String chineseChar) {
        return false;
    }

    /** Get the simplified character. **/
    public String getSimplified() {
        return this.simplified;
    }

    /** Get the traditional character. **/
    public String getTraditional() {
        return this.traditional;
    }

    /** Get the Cantonese pronunciation(s). **/
    public Set<String> getCantonesePronunciation() {
        return this.cantonesePronunciation;
    }

    /** Get the Mandarin pronunciation(s). **/
    public Set<String> getMandarinPronunciation() {
        return this.pinyin;
    }

    /** Get the example uses list. **/
    public Set<String> getExampleUses() {
        return new HashSet<>(this.exampleUses);
    }

    /** Get the audio file path. **/
    public Set<String> getAudioFile() {
        return this.audioFile;
    }

    /** Add a Cantonese pronunciation for this character.
     * @param pronunciation A String for the pronunciation.
     * **/
    public void addCantonesePronunciation(String pronunciation) {
        this.cantonesePronunciation.add(pronunciation);
    }

    /** Add a Mandarin pronunciation for this character.
     * @param pronunciation A String for the pronunciation.
     * **/
    public void addMandarinPronunciation(String pronunciation) {
        this.pinyin.add(pronunciation);
    }

    /**
     * Add an audio file for this character.
     * @param audioPath Path to the audio file.
     */
    public void addAudio(String audioPath) {
        this.audioFile.add(audioPath);
    }

    /**
     * Add the provided example to the example list.
     * @param example The example to add.
     */
    public void addExamples(String example) {
        this.exampleUses.add(example);
    }



}
