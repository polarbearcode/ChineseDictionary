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


    /**
     * Create a Chinese character. Saves to the provided dictionary.
     * If the character is already in the dictionary, update it if
     * the provided parameters are different.
     * @param simplified String for simplified character.
     * @param traditional String for traditional character.
     * @param cp    String for the cantonese pronunciation.
     * @param py    String for the pinyin.
     */
    public ChineseCharacter(String simplified, String traditional, String cp,
                            String py) {


        this.simplified = simplified;
        this.traditional = traditional;

        this.cantonesePronunciation = new HashMap<>();
        this.pinyin = new HashSet<>();

        this.pinyin.add(py);

        this.exampleUses = new HashSet<>();

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
    public Map<String, String> getCantonesePronunciation() {
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


    /** Add a Cantonese pronunciation for this character.
     * @param pronunciation A String for the pronunciation.
     * @param audio Path to corresponding audio file.
     * **/
    public void addCantonesePronunciation(String pronunciation, String audio) {
        this.cantonesePronunciation.put(pronunciation, audio);
    }

    /** Add a Mandarin pronunciation for this character.
     * @param pronunciation A String for the pronunciation.
     * **/
    public void addMandarinPronunciation(String pronunciation) {
        this.pinyin.add(pronunciation);
    }

    /**
     * Add the provided example to the example list.
     * Do nothing if the example does not contain the character.
     * @param example The example to add.
     */
    public void addExamples(String example) {
        if (example.contains(this.simplified) || example.contains(this.traditional)) {
            this.exampleUses.add(example);
        }
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

    @Override
    public String toString() {
        return this.simplified;
    }
}
