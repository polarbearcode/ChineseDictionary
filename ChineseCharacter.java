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

    /** A Map for the Cantonese pronunciation(s) of the character **/
    private Set<String> cantonesePronunciation;

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

        this(simplified, traditional, new HashSet<>(Arrays.asList(cp)),
                new HashSet<>(Arrays.asList(py)), new HashSet<>());

    }

    /**
     * Create a Chinese character. User inputs a list of pronunciations.
     * Saves to the provided dictionary.
     * If the character is already in the dictionary, update it if
     * the provided parameters are different.
     * @param simplified String for simplified character.
     * @param traditional String for traditional character.
     * @param cp    Set of the cantonese pronunciation(s).
     * @param py    Set of the pinyin pronunciation(s).
     * @param examples   Set of example(s)
     */
    public ChineseCharacter(String simplified, String traditional, Set<String> cp,
                            Set<String> py, Set<String> examples) {


        this.simplified = simplified;
        this.traditional = traditional;

        this.cantonesePronunciation = new HashSet<>(cp);
        this.pinyin = new HashSet<>(py);

        this.exampleUses = new HashSet<>(examples);

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
