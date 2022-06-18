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

        super(mapPath, new GetCantonesePronunciation(), Pattern.compile("([a-zA-Z]+)([1-6])"));

        this.addToneCharacters(1, "番");
        this.addToneCharacters(2,"茄");
        this.addToneCharacters(3, "酱");
        this.addToneCharacters(4, "牛");
        this.addToneCharacters(5, "腩");
        this.addToneCharacters(6, "面");

    }

}
