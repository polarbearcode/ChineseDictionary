package Screens;

import Characters.PronunciationAndTone;

/** Screen that displays pronunciation -> character mapping and allows user to edit it. **/
public class PronunciationMappingEditor implements DictionaryScreen{

    PronunciationAndTone p;

    /**
     * Instantiate a PronunciationEditorScreen either for Cantonese or Mandarin mappings.
     * @param p A PronunciationAndTone object to get the mapping information for the scree.
     */
    public PronunciationMappingEditor(PronunciationAndTone p) {
        this.p = p;
    }

    @Override
    public void drawToScreen() {
        
    }


}
