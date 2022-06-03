import java.io.*;
import java.util.HashSet;
import java.util.Set;

/** Represents the current dictionary of all the characters. **/
public abstract class CharacterList {
    private static String dictionaryPath = "./dictionary.srl";
    private static Set<ChineseCharacter> dictionary;

    /** Returns the current dictionary of all the characters added. */
    public static Set<ChineseCharacter> getDictionary() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dictionaryPath));
            CharacterList.dictionary = (Set<ChineseCharacter>) ois.readObject();
            ois.close();
            return CharacterList.dictionary;
        } catch (IOException | ClassNotFoundException io) {
            initializeDictionary();
            return CharacterList.dictionary;
        }
    }

    /**
     * Initialize dictionary file if it doesn't already exist.
     */
    private static void initializeDictionary() {
        try {
            String fileName = dictionaryPath;
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            CharacterList.dictionary = new HashSet<>();
            oos.writeObject(CharacterList.dictionary);
            oos.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    /**
     * Add a character to the dictionary.
     * @param c The character to add.
     */
    public static void addCharacter(ChineseCharacter c) {
        Set<ChineseCharacter> curDictionary = getDictionary();
        curDictionary.add(c);
        writeDictionaryToFile(curDictionary);
    }

    /**
     * Save the current state of the dictionary to a file.
     * @param curDictionary A Set representing the dictionary to save.
     */
    private static void writeDictionaryToFile(Set<ChineseCharacter> curDictionary) {
        try {
            String fileName = dictionaryPath;
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(CharacterList.dictionary);
            oos.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Remove the specified character from the dictionary.
     * @param c The character to remove.
     */
    public static void removeChar(ChineseCharacter c) {
        Set<ChineseCharacter> curDictionary = getDictionary();
        curDictionary.remove(c);
        writeDictionaryToFile(curDictionary);
    }

    /**
     * For testing, update the dictionary file path.
     */
    public static void updateDictionaryFile() {
         CharacterList.dictionaryPath = "./testDictionary.srl";
    }

}
