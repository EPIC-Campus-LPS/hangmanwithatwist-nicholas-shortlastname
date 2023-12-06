import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class runs a game of Hangman using the Hangman class.
 * It is responsible for the console program that players use to interact with the game.
 *
 * @author
 */
public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        // check if the length inputted is valid
        Dictionary dictionary = new Dictionary(); Scanner scanner = new Scanner(System.in);
        boolean validLength = false;
        while (validLength == false){
            System.out.println("PLease enter a valid word length: ");
            validLength = dictionary.validWordLength(scanner.nextInt());
        }

        int wrongGuesses = 0;
        String[] hangedManImages = new String[]{"   \n   \n   ", " o \n   \n   ", " o \n | \n   "," o \n-| \n   ", " o \n-|-\n   ", " o \n-|-\n/  ", " o \n-|-\n/ \\"};
        while(wrongGuesses <= 6){

        }
    }
}