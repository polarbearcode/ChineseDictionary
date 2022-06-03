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
    public Map<String, ChineseCharacter> getDictionary() {
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
        Map<String, ChineseCharacter> curDictionary = getDictionary();
        curDictionary.put(c.getSimplified(), c);
        writeDictionaryToFile(curDictionary);
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
     * @param c The character to remove.
     */
    public void removeChar(ChineseCharacter c) {
        Map<String, ChineseCharacter> curDictionary = getDictionary();
        curDictionary.remove(c);
        writeDictionaryToFile(curDictionary);
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
            chineseChar.addCantonesePronunciation(pronunciation, audio);
        }

        writeDictionaryToFile(this.dictionary);
    }

    /**
     * Look up the specified character in the dictionary.
     * @param chineseChar   The simplified chinese character to look up.
     * @return  The ChineseCharacter object containing all the information.
     */
    public ChineseCharacter lookUp(String chineseChar) {
        return null;
    }



}
