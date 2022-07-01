package Characters;

import java.util.regex.Pattern;

/** Find the character combo that matches the pinyin. **/
public class MPAndT extends PronunciationAndTone{
    /** Instantiate the toneCharacters and pronunCharacters mapping.
     * Set file to read and save to mapPath.
     * @param mapPath   String, path to the HashMap serializable **/
    public MPAndT(String mapPath) {
        super(mapPath, new GetMandarinPronunciation(), Utils.mandarinPronunciationPattern);
        this.addToneCharacters(0, "的");
        this.addToneCharacters(1, "中");
        this.addToneCharacters(2, "国");
        this.addToneCharacters(3, "好");
        this.addToneCharacters(4, "快");
    }
}
