package Characters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.*;

/** Stores two characters: one for pronunciation and one for tone. Like 切韵 **/
public abstract class PronunciationAndTone {

    /** A HashMap containing the mapping for tones to characters. **/
    private HashMap<Integer, String> toneCharacters;

    /** A HashMap containing the mapping for pronunciation to characters **/
    private HashMap<String, String> pronunCharacters;

    /** The reverse of the proonunCharacters mapping. **/
    private HashMap<String, String> reversePronounChars;

    /** The path to the pronunciation character mapping HashMap serialized object. **/
    private String mapPath;

    /** Function to use in character combo to get either cantonese or mandarin pronunciation. **/
    private GetPronunciationLanguage getPronunciationLanguage;

    /** The pattern to use for the pronunciation split regex. **/
    private Pattern pronunciationPattern;

    /** Instantiate a PronunciationAndTone object that reads in from the file in the provided
     * file path and uses the specified getPronunciationLanguage implementation.
     */
    public PronunciationAndTone(String mapPath, GetPronunciationLanguage getPronunciationLanguage,
                         Pattern pronunciationPattern) {
        this.pronunCharacters = new HashMap<>();
        this.toneCharacters = new HashMap<>();
        this.mapPath = mapPath;
        this.getPronunciationLanguage = getPronunciationLanguage;
        this.pronunciationPattern = pronunciationPattern;
        readInPronunMap();

        for (var entry : this.pronunCharacters.entrySet()) {
            this.reversePronounChars.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Returns a combination of characters that equal to the pronunciation of the provided character.
     * @param chineseCharacter The character to find the pronunciation for.
     * @return A list of length 2 arrays where the 0 element is the equivalent pronunciation,
     *          the 1 index element is the equivalent tone.
     */
    public List<String[]> characterCombo(ChineseCharacter chineseCharacter) {
        boolean needUpdate = false;

        List<String[]> toReturn = new ArrayList<>();

        for (String pronunciation : this.getPronunciationLanguage.getPronunciation(chineseCharacter)) {
            String[] toAdd = new String[2];
            String[] split = this.pronunciationSplit(pronunciation);

            if (!this.getPronunCharacters().containsKey(split[0])) {
                this.addPronunciationMapping(split[0], chineseCharacter.getTraditional());
                needUpdate = true;
            }

            toAdd[0] = this.getPronunCharacters().get(split[0]);
            toAdd[1] = this.getToneMapping().getOrDefault(Integer.valueOf(split[1]), "");

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
        this.reversePronounChars.put(chineseCharacter, pronunciation);
        this.updatePronunciationMap();
    }

    /**
     * Remove the pronunciation from the mapping.
     * @param pronunciation String for the pronunciation.
     */
    public void deleteCharacterMapping(String pronunciation) {
        this.pronunCharacters.remove(pronunciation);
        this.reversePronounChars.remove(pronunciation);
        this.updatePronunciationMap();
    }


    private void readInPronunMap() {
        try {
            File pronunciationMap = new File(this.mapPath);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pronunciationMap));
            this.pronunCharacters = (HashMap<String, String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            this.pronunCharacters = new HashMap<>();
            updatePronunciationMap();
        }
    }



    /**
     * Add a pronunciation mapping to the pronunciation map. Updates the mapping if the
     * pronunciation is already in the map.
     * @param pronunciation String, pronunciation "aaa"
     * @param chineseChar String, the simplified Chinese character.
     */
    public void addPronunciationMapping(String pronunciation, String chineseChar) {
        this.pronunCharacters.put(pronunciation,chineseChar);
        this.reversePronounChars.put(chineseChar, pronunciation);
        updatePronunciationMap();
    }

    /**
     * Updates the pronunciation map file.
     */
    public void updatePronunciationMap() {
        Utils.writeFile(this.mapPath, this.pronunCharacters);

    }

    /**
     * A helper function to split the pronunciation into pronunciation and tone.
     * @param pronunciation A String representing the pronunciation e.g. "hon1";
     * @return A length 2 array where the 0-index element is the pronunciation and
     * the 1-index element is the tone number as a string.
     */
    private String[] pronunciationSplit(String pronunciation) {
        String[] split = new String[2];
        Matcher m = this.pronunciationPattern.matcher(pronunciation);

        if (m.find()) {
            split[0] = m.group(1);
            split[1] = m.group(2);
        }

        if (split[1].equals("")) {
            split[1] = "0";
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

    public void clearMapping() {
        Iterator<Map.Entry<String, String>> itr = pronunCharacters.entrySet().iterator();

        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }

        updatePronunciationMap();
    }

    /**
     * Add the tone -> chineseCharacter mapping to the toneCharacter map.
     * @param tone  The tone number.
     * @param chineseCharacter The corresponding Chinese character as a string.
     */
    public void addToneCharacters(Integer tone, String chineseCharacter) {
        this.toneCharacters.put(tone, chineseCharacter);
    }


    /** Get the tone mapping. **/
    public Map<Integer, String> getToneMapping() {
        return this.toneCharacters;
    }



}
