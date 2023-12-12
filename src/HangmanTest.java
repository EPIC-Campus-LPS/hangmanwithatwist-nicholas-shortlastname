import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {
    Dictionary dictionary = new Dictionary();
    Hangman game;
    public HangmanTest() throws FileNotFoundException {
        this.dictionary = dictionary;
        game = new Hangman(2,99);
    }

    @org.junit.jupiter.api.Test
    void guess() {
        game.guess('a',dictionary.getWords());
        assertEquals("be bi bo by de do ef eh el em en er es et ex go he hi hm ho id if in is it jo li lo me mi mm mo mu my ne no nu od oe of oh om on op or os ow ox oy pe pi re sh si so ti to uh um un up us ut we wo xi xu ye ", dictionary.printDictionary());
    }

    @org.junit.jupiter.api.Test
    void guessesRemain() {
    }

    @org.junit.jupiter.api.Test
    void hasGuesses() {
    }

    @org.junit.jupiter.api.Test
    void getRevealedLetters() {
    }

    @org.junit.jupiter.api.Test
    void getIncorrectGuesses() {
    }
}