import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
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
            dictionary.setupDictionary(length);

            // check if a guess amount inputted is valid
            int guessAmount = -1;
            while (guessAmount <= 0){
                System.out.print("Enter a valid guess amount: ");
                guessAmount = scanner.nextInt();
            }
            //ask if user wants a running number of words
            System.out.print("Do you want a running total of remaining words? (yes/no): ");
            boolean runTotal = scanner.next().contains("yes");

            Hangman game = new Hangman(length, guessAmount);
            String guess;
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            LinkedList<String> prevGuesses = new LinkedList<>();

            //game of hangman, keeps running until word guessed or out of guesses
            while (game.hasGuesses() && game.getRevealedLetters().contains("-")) {
                guess = " ";
                System.out.println("\n\nYou have " + game.guessesRemain() + " guesses left");
                System.out.println("You have guessed: " + prevGuesses.toString());
                System.out.println("Your guesses have filled in: " + game.getRevealedLetters());
                if(runTotal){
                    System.out.println("There are currently " + dictionary.getWords().size() + " word(s) possible");
                }

                System.out.println(dictionary.printDictionary());

                while (!(guess.length() == 1 && alphabet.contains(guess) && !prevGuesses.contains(guess))) {
                    System.out.print("Enter a valid alphabetical character that you have not guessed: ");
                    guess = scanner.next().toLowerCase();
                }

                prevGuesses.add(guess);
                dictionary.setWords(game.guess(guess.charAt(0), dictionary.getWords()));
            }

            //if person is correct or not
            System.out.println("\n\n");
            if(!game.getRevealedLetters().contains("-")){
                System.out.println("Congratulations! You guessed the word \"" + game.getRevealedLetters() + "\" in " + game.getIncorrectGuesses() + " guesses.");
            }else if (!game.hasGuesses()){
                System.out.println("Darn! You ran out of guesses. Your word was " + dictionary.getWords().get((int) Math.random() * dictionary.getWords().size()));
            }

            //ask to play again
            System.out.println("\n\nDo you want to play again? (yes/no): ");
            running = scanner.next().contains("yes");
        }
    }
}