import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {
    Dictionary dictionary = new Dictionary();;
    public DictionaryTest() throws FileNotFoundException {
        this.dictionary = dictionary;
    }

    @Test
    void validWordLength() {
        assertAll(() -> assertEquals(true, dictionary.validWordLength(5)),
                () -> assertEquals(false, dictionary.validWordLength(31)));
    }

    @Test
    void setupDictionary() {
        dictionary.setupDictionary(2);
        assertEquals(94, dictionary.getWords().size());
    }

    @Test
    void printDictionary() {
        dictionary.setupDictionary(2);
        assertEquals("aa ad ae ag ah ai al am an ar as at aw ax ay ba be bi bo by da de do ef eh el em en er es et ex fa go ha he hi hm ho id if in is it jo ka la li lo ma me mi mm mo mu my na ne no nu od oe of oh om on op or os ow ox oy pa pe pi re sh si so ta ti to uh um un up us ut we wo xi xu ya ye ", dictionary.printDictionary());
    }

    @Test
    void setWords() {
        LinkedList<String> linkedList = new LinkedList<>(); linkedList.add("ally");
        dictionary.setWords(linkedList);
        assertEquals(linkedList, dictionary.getWords());
    }
    @Test
    void getWords() {
        LinkedList<String> linkedList = new LinkedList<>(); linkedList.add("ally");
        dictionary.setWords(linkedList);
        assertEquals(linkedList, dictionary.getWords());
    }
}