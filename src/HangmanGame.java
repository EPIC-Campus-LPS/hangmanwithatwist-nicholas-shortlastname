import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class runs a game of Hangman using the Hangman class.
 * It is responsible for the console program that players use to interact with the game.
 *
 * @author
 */
public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        boolean running = true;
        while (running){
            Dictionary dictionary = new Dictionary(); Scanner scanner = new Scanner(System.in);

            // check if a length inputted is valid
            boolean validLength = false; int length = 0;
            while (validLength == false) {
                System.out.print("Enter a valid word length: ");
                length = scanner.nextInt();
                validLength = dictionary.validWordLength(length);
            }
            // check if a guess amount inputted is valid
            int guessAmount = -1;
            while (guessAmount <= 0){
                System.out.print("Enter a valid guess amount: ");
                guessAmount = scanner.nextInt();
            }
            //ask if user wants a running number of words
            System.out.print("Do you want a running total of remaining words? (y/n): ");
            String runningTotal = scanner.next();
            boolean runTotal = runningTotal == "y";
            System.out.println(runningTotal);
            System.out.println("y");
            System.out.println(runningTotal == "y ");

            Hangman game = new Hangman(dictionary, length, guessAmount);
            String guess;
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            boolean correctGuess = false;
            LinkedList<String> prevGuesses = new LinkedList<>();
            //game of hangman, keeps running until word guessed or out of guesses
            while (game.hasGuesses() && !correctGuess) {
                guess = " ";
                System.out.println("You have " + game.guessesRemain() + " guesses left");
                System.out.println("You have guessed: " + prevGuesses.toString());
                System.out.println("Your guesses have filled in:" + game.getRevealedLetters());
                if(runTotal){
                    System.out.println("There are currently " + dictionary.getWords().size() + "words possible");
                }

                while (!(guess.length() == 1 && alphabet.contains(guess) && !prevGuesses.contains(guess))) {
                    System.out.print("Guess a valid alphabetical character: ");
                    guess = scanner.next();
                }

                prevGuesses.add(guess);
                correctGuess = game.guess(guess.charAt(0));
            }
        }
    }
}