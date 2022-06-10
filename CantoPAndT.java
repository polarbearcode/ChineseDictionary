import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Finds the character combo for the Cantonese Pronunciation. **/
public class CantoPAndT implements PronunciationAndTone {

    /** A HashMap containing the mapping for tones to characters. **/
    private HashMap<Integer, String> toneCharacters;

    /** A HashMap containing the mapping for pronunciation to characters **/
    private HashMap<String, String> pronunCharacters;

    /** The path to the pronunciation character mapping HashMap serialized object. **/
    private String mapPath;

    /** Instantiate the toneCharacters and pronunCharacters mapping.
     * Set file to read and save to mapPath.
     * @param mapPath   String, path to the HashMap serializable **/
    public CantoPAndT(String mapPath) {

        this.toneCharacters = new HashMap<>();

        this.toneCharacters.put(1, "番");
        this.toneCharacters.put(2,"茄");
        this.toneCharacters.put(3, "酱");
        this.toneCharacters.put(4, "牛");
        this.toneCharacters.put(5, "腩");
        this.toneCharacters.put(6, "面");

        this.mapPath = mapPath;

        readInPronunMap();

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

        for (String pronunciation : chineseCharacter.getCantonesePronunciation()) {
            String[] toAdd = new String[2];
            String[] split = this.pronunciationSplit(pronunciation);

            if (!this.pronunCharacters.containsKey(split[0])) {
                this.pronunCharacters.put(split[0], chineseCharacter.getTraditional());
                needUpdate = true;
            }

            toAdd[0] = this.pronunCharacters.get(split[0]);
            toAdd[1] = this.toneCharacters.get(Integer.valueOf(split[1]));

            toReturn.add(toAdd);
        }

        if (needUpdate) {
            this.updatePronunciationMap();
        }

        return toReturn;
    }

    /**
     * Change the character associated with the pronunciation.
     * If the pronunciation doesn't exist yet, create the mapping.
     * @param pronunciation String, the key in the dictionary for the pronunciation
     * @param chineseCharacter  String, the character to change the mapping to.
     */
    public void changeCharacterMapping(String pronunciation, String chineseCharacter) {
        this.pronunCharacters.put(pronunciation, chineseCharacter);
        this.updatePronunciationMap();
    }

    /**
     * Remove the pronunciation from the mapping.
     * @param pronunciation String for the pronunciation.
     */
    public void deleteCharacterMapping(String pronunciation) {
        this.pronunCharacters.remove(pronunciation);
        this.updatePronunciationMap();
    }


    private void readInPronunMap() {
        try {
            File cantonesePronunciationMap = new File(this.mapPath);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cantonesePronunciationMap));
            this.pronunCharacters = (HashMap<String, String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            this.pronunCharacters = new HashMap<>();
            updatePronunciationMap();
        }
    }

    /**
     * Updates the pronunciation map file.
     */
    void updatePronunciationMap() {
        WriteFile.writeFile(this.mapPath, this.pronunCharacters);

    }

    /**
     * A helper function to split the pronunciation into pronunciation and tone.
     * @param pronunciation A String representing the pronunciation e.g. "hon1";
     * @return A length 2 array where the 0-index element is the pronunciation and
     * the 1-index element is the tone number as a string.
     */
    private String[] pronunciationSplit(String pronunciation) {
        String[] split = new String[2];
        Pattern p = Pattern.compile("([a-zA-Z]+)([1-6])");
        Matcher m = p.matcher(pronunciation);

        if (m.find()) {
            split[0] = m.group(1);
            split[1] = m.group(2);
        }

        return split;
    }

    /** Return a copy of the pronunCharacters mapping. **/
    public Map<String, String> getPronunCharacters() {
        HashMap<String, String> copy = new HashMap<>();
        copy.putAll(this.pronunCharacters);
        return copy;
    }

    /** Return the pronounCharacters mapping (not a copy) for testing. **/
    Map<String, String> getPronounCharactersTest() {
        return this.pronunCharacters;
    }

}
