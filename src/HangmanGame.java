import java.io.FileNotFoundException;

/**
 * This class runs a game of Hangman using the Hangman class.
 * It is responsible for the console program that players use to interact with the game.
 *
 * @author
 */
public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        Dictionary dictionary = new Dictionary();
        System.out.println(dictionary.printDictionary());
    }
}