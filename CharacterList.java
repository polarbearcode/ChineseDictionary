import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CharacterList {
    private static Set<ChineseCharacter> dictionary;

    /** Returns the current dictionary of all the characters added. */
    public static Set<ChineseCharacter> getDictionary() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("." + "/dictionary.srl"));
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
            String fileName = "./dictionary.srl";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            CharacterList.dictionary = new HashSet<>();
            oos.writeObject(CharacterList.dictionary);
            oos.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

}
