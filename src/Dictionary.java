import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class creates a dictionary for the Hangman game.
 * It reads in data from dictionary.txt and manages a data structure of words for Hangman
 *
 * @author
 */
public class Dictionary {
    public String[] wordList;
    public Dictionary() throws FileNotFoundException {
        String words = "";
        try {
            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\hangmanwithatwist-nicholas-shortlastname\\dictionary.txt"));
            while (file.hasNextLine()) {
                words += file.next() + " ";
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Cannot find dictionary.txt");
        }
        this.wordList = words.split(" ");
    }
    public String printDictionary(){
        String output = "";
        for(String word : wordList){
            output += word + " ";
        }
        return output;
    }
}
