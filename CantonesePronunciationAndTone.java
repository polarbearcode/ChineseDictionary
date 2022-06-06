import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Finds the character combo for the Cantonese Pronunciation. **/
public class CantonesePronunciationAndTone implements PronunciationAndTone {

    /** A HashMap containing the mapping for tones to characters. **/
    private HashMap<Integer, String> toneCharacters;

    /** A HashMap containing the mapping for pronunciation to characters **/
    private HashMap<String, String> pronunCharacters;

    /** Instantiate the toneCharacters and pronunCharacters mapping. **/
    public CantonesePronunciationAndTone() {

        this.toneCharacters = new HashMap<>();

        this.toneCharacters.put(1, "番");
        this.toneCharacters.put(2,"茄");
        this.toneCharacters.put(3, "酱");
        this.toneCharacters.put(4, "牛");
        this.toneCharacters.put(5, "腩");
        this.toneCharacters.put(6, "面");


        this.pronunCharacters = initializePronunciationMap();
    }

    /**
     * Returns a combination of characters that equal to the pronunciation of the provided character.
     * @param chineseCharacter The character to find the pronunciation for.
     * @return A list of length 2 arrays where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    @Override
    public List<String[]> characterCombo(ChineseCharacter chineseCharacter) {

        boolean needUpdate = false;

        List<String[]> toReturn = new ArrayList<>();

        for (String pronunciation : chineseCharacter.getCantonesePronunciation().keySet()) {
            String[] toAdd = new String[2];
            String[] split = this.pronunciationSplit(pronunciation);

            if (!this.pronunCharacters.containsKey(split[0])) {
                this.pronunCharacters.put(pronunciation, chineseCharacter.getSimplified());
                needUpdate = true;
            }

            toAdd[0] = this.pronunCharacters.get(split[0]);
            toAdd[1] = this.pronunCharacters.get(split[1]);

            toReturn.add(toAdd);
        }

        if (needUpdate) {
            this.updatePronunciationMap();
        }

        return toReturn;

    }

    /**
     * Read in the pronunciation map file or create one if it does not exist yet.
     * @return  The saved pronunciation map.
     */
    private HashMap<String, String> initializePronunciationMap() {
        return null;
    }

    /**
     * Updates the pronunciation map file.
     */
    private void updatePronunciationMap() {
        ;
    }

    /**
     * A helper function to split the pronunciation into pronunciation and tone.
     * @param pronunciation A String representing the pronunciation e.g. "hon1";
     * @return A length 2 array where the 0-index element is the pronunciation and
     * the 1-index element is the tone number as a string.
     */
    private String[] pronunciationSplit(String pronunciation) {
        return null;
    }
}
