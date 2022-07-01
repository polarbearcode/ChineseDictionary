import java.io.*;
import java.util.*;



/** Represents the current dictionary of all the characters. **/
public class CharacterList {
    private final String dictionaryPath;
    private Map<String, ChineseCharacter> dictionary;

    /**
     * Instantiate a CharacterList representing the specified dictionary.
     * @param dictionaryPath String for the path to the dictionary file.
     */
    public CharacterList(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
        this.getDictionary();
    }

    /** Returns the current dictionary of all the characters added. */
    private void getDictionary() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dictionaryPath));
            this.dictionary = (HashMap<String, ChineseCharacter>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException io) {
            initializeDictionary();
        }
    }

    /**
     * Initialize dictionary file if it doesn't already exist.
     */
    private void initializeDictionary() {
        this.dictionary = new HashMap<>();
        writeDictionaryToFile(this.dictionary);
    }

    /**
     * Add a character to the dictionary.
     * @param c The character to add.
     */
    public void addCharacter(ChineseCharacter c) {
        if (this.dictionary.containsKey(c.getSimplified())) {
            ChineseCharacter charInDictionary = this.dictionary.get(c.getSimplified());
            charInDictionary.getCantonesePronunciation().addAll(c.getCantonesePronunciation());
            charInDictionary.getMandarinPronunciation().addAll(c.getMandarinPronunciation());
            this.dictionary.put(c.getSimplified(), charInDictionary);

            for (String example : charInDictionary.getExampleUses()) {
                charInDictionary.removeExample(example);
            }

            for (String example : c.getExampleUses()) {
                charInDictionary.addExamples(example);
            }

        } else {
            this.dictionary.put(c.getSimplified(), c);
        }

        writeDictionaryToFile(this.dictionary);
    }

    /**
     * Save the current state of the dictionary to a file.
     * @param curDictionary A HashMap representing the dictionary to save.
     */
    void writeDictionaryToFile(Map<String, ChineseCharacter> curDictionary) {
        Utils.writeFile(this.dictionaryPath, curDictionary);
    }

    /**
     * Remove the specified character from the dictionary.
     * @param c String for The simplified character to remove.
     */
    public void removeChar(String c) {
        this.dictionary.remove(c);
        writeDictionaryToFile(this.dictionary);
    }

    /**
     * Add a Cantonese pronunciation to the character. Do nothing if the character is not in the
     * dictionary.
     * @param chineseChar   The simplified character.
     * @param pronunciation The Cantonese pronunciation.
     * @param audio The audio file.
     */
    public void addCantonesePronunciation(ChineseCharacter chineseChar, String pronunciation, String audio) {
        if (this.dictionary.containsKey(chineseChar.getSimplified())) {
            ChineseCharacter charInDict = this.dictionary.get(chineseChar.getSimplified());
            charInDict.addCantonesePronunciation(pronunciation);
        }

        writeDictionaryToFile(this.dictionary);
    }

    /**
     * Look up the specified character in the dictionary.
     * @param chineseChar   The simplified chinese character to look up.
     * @return  The ChineseCharacter object containing all the information. Return null if
     * character is not in the dictionary.
     */
    public ChineseCharacter lookUp(String chineseChar) {
        return this.dictionary.getOrDefault(chineseChar, null);
    }

    /**
     * Add an example usage for the specified character. Do nothing if character is not in the dictionary.
     * @param chineseChar String simplified Chinese character.
     * @param example   String, example usage to add.
     */
    public void addExample(String chineseChar, String example) {
        if (this.dictionary.containsKey(chineseChar) && example.contains(chineseChar)) {
           ChineseCharacter charInDict = this.dictionary.get(chineseChar);
           charInDict.addExamples(example);
        }
    }

    /**
     * Add an example usage for the specified character. Do nothing if character is not in the dictionary.
     * @param chineseChar ChineseCharacter object.
     * @param example   String, example usage to add.
     */
    public void addExample(ChineseCharacter chineseChar, String example) {
        this.addExample(chineseChar.getSimplified(), example);
    }

    /**
     * Remove an example form the provided chinese character.
     * Do nothing if the character is not in the dictionary or there is no such example for that character.
     * @param chineseChar   String, the simplified chinese character
     * @param example String, the example to remove.
     */
    public void removeExample(String chineseChar, String example) {
        if (this.dictionary.containsKey(chineseChar)) {
            this.lookUp(chineseChar).removeExample(example);
        }
    }

    /**
     * Remove an example form the provided chinese character.
     * Do nothing if the character is not in the dictionary or there is no such example for that character.
     * @param chineseChar   ChineseCharacter object, the simplified chinese character
     * @param example String, the example to remove.
     */
    public void removeExample(ChineseCharacter chineseChar, String example) {
        this.removeExample(chineseChar.getSimplified(), example);
    }

    /**
     * Find the audio file for the Cantonese pronunciation(s) of a character.
     * @param chineseCharacter The character to find the pronunciations for.
     * @return A Map of pronunciation->audio file path pairs. Map a pronunciation to null if the audio file
     * for that pronunciation doesn't exist.
     */
    public Map<String, String> findAudio(ChineseCharacter chineseCharacter) {
        if (this.lookUp(chineseCharacter.getSimplified()) == null) {
            this.addCharacter(chineseCharacter);
            this.writeDictionaryToFile(this.dictionary);
        }

        Map<String, String> toReturn = new HashMap<>();

        for (String p : chineseCharacter.getCantonesePronunciation()) {
            String audioFilePath = "./Audio" + "/" + p + ".mp3";
            File f = new File(audioFilePath);
            if (f.exists()) {
                toReturn.put(p, audioFilePath);
            } else {
                toReturn.put(p, null);
            }
        }

        return toReturn;

    }

    /**
     * Get the current dictionary.
     * @return  The HashMap dictionary.
     */
    public Map<String, ChineseCharacter> getCurrentDictionary() {
        return this.dictionary;
    }

    /**
     * Get the current size of the dictionary.
     * @return  The number of characters in the HashMap.
     */
    public int size() {
        return this.dictionary.size();
    }

    void clearDictionary() {
        Iterator<Map.Entry<String, ChineseCharacter>> itr = dictionary.entrySet().iterator();

        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }

        writeDictionaryToFile(dictionary);
    }

}
