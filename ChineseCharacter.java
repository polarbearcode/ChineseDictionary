import java.io.Serializable;
import java.util.*;

/** Represents a Chinese character with simplified and traditional character, audio file,
 * and an example sentence/pairs.
 */
public class ChineseCharacter implements Serializable {

    /** String for simplified character. **/
    private String simplified;

    /**String for the traditional character. **/
    private String traditional;

    /** A Map for the Cantonese pronunciation of the character and the relevant audio file. **/
    private Map<String, String> cantonesePronunciation;

    /** Strings for the pinyin pronunciation of the character. **/
    private Set<String> pinyin;

    /** Sentences and/or phrases using the character. **/
    private Set<String> exampleUses;

    /** Pronunciation using other Chinese characters. **/
    private PronunciationAndTone pronunciationWithCharacters;

    /** The current dictionary of all the characters. **/
    private Set<ChineseCharacter> dictionary;


    /**
     * Create a Chinese character. If the character is already in the dictionary, update it if
     * the provided parameters are different.
     * @param simplified String for simplified character.
     * @param traditional String for traditional character.
     * @param cp    String for the cantonese pronunciation.
     * @param py    String for the pinyin.
     * @param audio A String representing the path to the audio file in the Audio folder.
     */
    public ChineseCharacter(String simplified, String traditional, String cp, String py, String audio) {

        this.dictionary = CharacterList.getDictionary();

        if (checkIfAlreadyAdded(simplified)) {
            if (!this.cantonesePronunciation.keySet().contains(cp)) {
                this.cantonesePronunciation.put(cp, audio);
            }

            if (!this.pinyin.contains(py)) {
                this.pinyin.add(py);
            }
        } else {

            this.simplified = simplified;
            this.traditional = traditional;

            this.cantonesePronunciation = new HashMap<>();
            this.pinyin = new HashSet<>();

            this.cantonesePronunciation.put(cp, audio);
            this.pinyin.add(py);
        }
    }

    /**
     * Checks if the provided character is already in the dictionary.
     * @param chineseChar The character to check.
     * @return  True if the character is already in the dictionary, false otherwise.
     */
    private boolean checkIfAlreadyAdded(String chineseChar) {
        return this.dictionary.contains(chineseChar);
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

    /**
     * Finds the combo of characters to make the PronunciationAndTone object.
     * @param pronunciation The Cantonese or Mandarin pronunciation.
     */
    private PronunciationAndTone findPronunciationCombo(String pronunciation) {
        // some regex
        return null;
    }
    @Override
    public int hashCode() {
        char c = this.simplified.charAt(0);
        int x = (int) c;
        return (int) c;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().toString().equals(this.getClass().toString())){
            return false;
        }

        ChineseCharacter otherChar = (ChineseCharacter) o;

        return otherChar.hashCode() == this.hashCode();
    }
}
