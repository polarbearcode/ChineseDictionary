import java.io.*;
import java.util.HashSet;
import java.util.Set;

/** Represents the current dictionary of all the characters. **/
public class CharacterList {
    private String dictionaryPath;
    private Set<ChineseCharacter> dictionary;

    /**
     * Instantiate a CharacterList representing the specified dictionary.
     * @param dictionaryPath String for the path to the dictionary file.
     */
    public CharacterList(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    /** Returns the current dictionary of all the characters added. */
    public Set<ChineseCharacter> getDictionary() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dictionaryPath));
            this.dictionary = (Set<ChineseCharacter>) ois.readObject();
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
            this.dictionary = new HashSet<>();
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
        Set<ChineseCharacter> curDictionary = getDictionary();
        curDictionary.add(c);
        writeDictionaryToFile(curDictionary);
    }

    /**
     * Save the current state of the dictionary to a file.
     * @param curDictionary A Set representing the dictionary to save.
     */
    private void writeDictionaryToFile(Set<ChineseCharacter> curDictionary) {
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
        Set<ChineseCharacter> curDictionary = getDictionary();
        curDictionary.remove(c);
        writeDictionaryToFile(curDictionary);
    }



}
