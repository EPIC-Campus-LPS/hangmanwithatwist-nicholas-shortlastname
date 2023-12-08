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
    int maxLen; int minLen;
    public Dictionary() throws FileNotFoundException {
        maxLen = 0; minLen = 0;
        try {
            String word;
            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\hangmanwithatwist-nicholas-shortlastname\\dictionary.txt"));
            while (file.hasNextLine()) {
                word = file.next();
                if(minLen == 0 || word.length() < minLen){
                    minLen = word.length();
                }
                if(maxLen == 0 || word.length() > maxLen){
                    maxLen = word.length();
                }
                words.add(word);
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Cannot find dictionary.txt");
        }
        this.words = words; this.maxLen = maxLen; this.minLen = minLen;
    }
    public boolean validWordLength(int length){
        if (length < minLen || length > maxLen){
            return false;
        }
        return true;
    }
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

    public LinkedList<String> getWords() {
        return words;
    }
    public void setWords(LinkedList newWords){
        words = newWords;
    }
    public String printDictionary(){
        String output = "";
        for(String word : words){
            output += word + " ";
        }
        return output;
    }
}
