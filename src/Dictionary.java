import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class creates a dictionary for the Hangman game.
 * It reads in data from dictionary.txt and manages a data structure of words for Hangman
 *
 * @author
 */
public class Dictionary {
    LinkedList<String> words = new LinkedList<>();
    LinkedList<Integer> validWordLengths = new LinkedList<>();
    public Dictionary() throws FileNotFoundException {
        try {
            String word;
            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\hangmanwithatwist-nicholas-shortlastname\\dictionary.txt"));
            while (file.hasNextLine()) {
                word = file.next();
                if(!validWordLengths.contains(word.length())){
                    validWordLengths.add(word.length());
                }
                words.add(word);
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Cannot find dictionary.txt");
        }
        this.words = words; this.validWordLengths = validWordLengths;
    }

    /**
     * Checks if there is a word with given length in the words from dictionary.txt
     * @param length length of word to be checked
     * @return if there is a word with that length
     */
    public boolean validWordLength(int length){
        if (validWordLengths.contains(length)){
            return true;
        }
        return false;
    }

    /**
     * Trims the dictionary to words only length letters long
     * @param chosenLength the length of which words will be included
     */
    public void setupDictionary(int chosenLength){
        LinkedList<String> wordsWithLength = new LinkedList<>();
        while (!words.isEmpty()){
            if (words.peek().length() == chosenLength){
                wordsWithLength.add(words.remove());
            }else{
                words.remove();
            }
        }

        words = wordsWithLength;
    }

    /**
     * Gets the current word list
     * @return word list
     */
    public LinkedList<String> getWords() {
        return words;
    }

    /**
     * Sets the current word list
     * @param newWords new word list
     */
    public void setWords(LinkedList newWords){
        words = newWords;
    }

    /**
     * Gets the word list in one string
     * @return string of word list
     */
    public String printDictionary(){
        String output = "";
        for(String word : words){
            output += word + " ";
        }
        return output;
    }
}
