import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Finds the character combo for the Cantonese Pronunciation. **/
public class CantoPAndT extends PronunciationAndTone {


    /** Instantiate the toneCharacters and pronunCharacters mapping.
     * Set file to read and save to mapPath.
     * @param mapPath   String, path to the HashMap serializable **/
    public CantoPAndT(String mapPath) {

        super(mapPath);

        this.addToneCharacters(1, "番");
        this.addToneCharacters(2,"茄");
        this.addToneCharacters(3, "酱");
        this.addToneCharacters(4, "牛");
        this.addToneCharacters(5, "腩");
        this.addToneCharacters(6, "面");

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

            if (!this.getPronunCharacters().containsKey(split[0])) {
               this.addPronunciationMapping(split[0], chineseCharacter.getTraditional());
                needUpdate = true;
            }

            toAdd[0] = this.getPronunCharacters().get(split[0]);
            toAdd[1] = this.getToneMapping().get(Integer.valueOf(split[1]));

            toReturn.add(toAdd);
        }

        if (needUpdate) {
            this.updatePronunciationMap();
        }

        return toReturn;
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


}
