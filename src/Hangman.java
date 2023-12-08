import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class represents a game of Hangman
 */
public class Hangman {
    private int incorrectGuesses = 0;
    private LinkedList<Character> lettersRevealed = new LinkedList<>();
    private Dictionary dictionary ;
    private int guessAmount;
    public Hangman(Dictionary dictionary, int wordLength, int guessAmount){
        this.incorrectGuesses = incorrectGuesses;
        this.lettersRevealed = lettersRevealed;
        this.dictionary = dictionary;
        this.guessAmount = guessAmount;
        for(int i = 0; i < wordLength; i ++){
            lettersRevealed.add(' ');
        }
    }
    public boolean guess(Character guess){
        LinkedList<String> wordsBefore = dictionary.getWords();

        // test if there is only one correct answer and it was guessed
        LinkedList<Character> testIfCorrect = lettersRevealed;
        if(wordsBefore.size() == 1){
            for (int i = 0; i < testIfCorrect.size(); i++){
                if(testIfCorrect.get(i) == null){
                    testIfCorrect.set(i, guess);
                }
            }
            if(listToString(testIfCorrect) == wordsBefore.get(0)){
                return true;
            }
        }

        // sort words into a hashmap of "word families"
        HashMap<LinkedList<Character>, LinkedList<String>> organizedWords = new HashMap<LinkedList<Character>, LinkedList<String>>();
        LinkedList<Character> guessFrequency;
        for (String word : wordsBefore) {
            guessFrequency = guessedFrequency(word, guess);
            if(organizedWords.get(guessFrequency) == null){
                organizedWords.put(guessFrequency, new LinkedList<String>());
                organizedWords.get(guessFrequency).add(word);
            } else {
                organizedWords.get(guessFrequency).add(word);
            }
        }

        // decide which word family is the biggest
        // https://stackoverflow.com/questions/4234985/how-to-for-each-the-hashmap

        LinkedList<Integer> familysizes = new LinkedList<>(); LinkedList<LinkedList<Character>> families = new LinkedList<>();
        organizedWords.forEach((k, v) -> families.add(k));
        organizedWords.forEach((k, v) -> familysizes.add(v.size()));

        int maxSize = 0; LinkedList<Character> maxSizedFamily = null;
        for(int i = 0; i < families.size(); i++){
            if(familysizes.get(i) > maxSize){
                maxSize = familysizes.get(i);
                maxSizedFamily = families.get(i);
            }
        }

        // once the biggest family is picked, setup to 'reveal' the letters, and set the dictionary to only have words in the chosen family
        System.out.println(maxSizedFamily.toString());
        addToRevealed(maxSizedFamily, guess);
        dictionary.setWords(organizedWords.get(maxSizedFamily));

        // check if the guess was 'incorrect'
        int numOfNulls = 0;
        for (Character character : maxSizedFamily){
            if(character == '0'){
                numOfNulls += 1;
            }
        }
        if (numOfNulls == maxSizedFamily.size()){
            incorrectGuesses += 1;
        }

        return false;
    }
    private LinkedList<Character> guessedFrequency(String word, Character guess){
        //give the word its "family", where 0 meaning the character is not in that spot and 1 meaning it is
        LinkedList<Character> isGuessThere = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == guess){
                isGuessThere.add('1');
            } else {
                isGuessThere.add('0');

            }
        }
        return isGuessThere;
    }
    private void addToRevealed(LinkedList<Character> charFreq, Character guess){
        for (int i = 0; i < charFreq.size(); i++) {
            if (charFreq.get(i) == '1'){
                lettersRevealed.set(i, guess);
            }
        }
    }
    private String listToString(LinkedList<Character> list){
        String output = "";
        for (Character character: list) {
            output += character;
        }
        return output;
    }
    public int guessesRemain(){
        return guessAmount - incorrectGuesses;
    }
    public boolean hasGuesses(){
        if(incorrectGuesses > guessAmount){
            return false;
        }
        return true;
    }
    public String getRevealedLetters(){
        String output = "";
        for(Character character : lettersRevealed){
            if(character == ' '){
                output += "-";
            }else{
                output += character;
            }
        }
        return output;
    }
}
