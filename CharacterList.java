import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Represents the current dictionary of all the characters. **/
public class CharacterList {
    private String dictionaryPath;
    private Map<String, ChineseCharacter> dictionary;

    /**
     * Instantiate a CharacterList representing the specified dictionary.
     * @param dictionaryPath String for the path to the dictionary file.
     */
    public CharacterList(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
        this.dictionary = this.getDictionary();
    }

    /** Returns the current dictionary of all the characters added. */
    private Map<String, ChineseCharacter> getDictionary() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dictionaryPath));
            this.dictionary = (HashMap<String, ChineseCharacter>) ois.readObject();
            ois.close();
            return this.dictionary;
        } catch (IOException | ClassNotFoundException io) {
            initializeDictionary();
            return this.dictionary;
        }
    }

    /**
     * Initialize dictionary file if it doesn't already exist.
     */
    private void initializeDictionary() {
        try {
            String fileName = dictionaryPath;
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            this.dictionary = new HashMap<>();
            oos.writeObject(this.dictionary);
            oos.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    /**
     * Add a character to the dictionary.
     * @param c The character to add.
     */
    public void addCharacter(ChineseCharacter c) {
        if (this.dictionary.containsKey(c.getSimplified())) {
            ChineseCharacter charInDictionary = this.dictionary.get(c.getSimplified());
            charInDictionary.getCantonesePronunciation().putAll(c.getCantonesePronunciation());
            charInDictionary.getMandarinPronunciation().addAll(c.getMandarinPronunciation());
            charInDictionary.getExampleUses().addAll(c.getExampleUses());
        } else {
            this.dictionary.put(c.getSimplified(), c);
            writeDictionaryToFile(this.dictionary);
        }
    }

    /**
     * Save the current state of the dictionary to a file.
     * @param curDictionary A HashMap representing the dictionary to save.
     */
    private void writeDictionaryToFile(Map<String, ChineseCharacter> curDictionary) {
        try {
            String fileName = dictionaryPath;
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dictionary);
            oos.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
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
            charInDict.addCantonesePronunciation(pronunciation, audio);
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
     * Do nothing if the character is not in the dictionary or there is no such example for that hcatacter.
     * @param chineseChar   String, the simplified chinese character
     * @param example String, the example to remove.
     */
    public void removeExample(String chineseChar, String example) {
        if (this.dictionary.containsKey(chineseChar)) {
            this.lookUp(chineseChar).getExampleUses().remove(example);
        }
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

}
