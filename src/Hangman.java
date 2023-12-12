import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class represents a game of Hangman
 */
public class Hangman {
    private int incorrectGuesses = 0;
    private LinkedList<Character> lettersRevealed = new LinkedList<>();
    private int guessAmount;
    public Hangman(int wordLength, int guessAmount){
        this.incorrectGuesses = incorrectGuesses;
        this.lettersRevealed = lettersRevealed;
        this.guessAmount = guessAmount;
        for(int i = 0; i < wordLength; i ++){
            lettersRevealed.add(' ');
        }
    }

    /**
     * Given a list of words and a guessed letter, calculate which group of words under which revealed letters would keep the group of words the biggest
     * returns the words, keeps the revealed letters internal, but it can be called with getRevealedLetters
     * @param guess guessed letter
     * @param words group of words
     * @return biggest group of words after being sorted by the frequency of the guess letter
     */
    public LinkedList<String> guess(Character guess, LinkedList<String> words){
        LinkedList<String> wordsBefore = words;

        // test if there is only one correct answer and it was guessed
        LinkedList<Character> testIfCorrect = lettersRevealed;
        if(wordsBefore.size() == 1){
            for (int i = 0; i < testIfCorrect.size(); i++){
                if(testIfCorrect.get(i) == null){
                    testIfCorrect.set(i, guess);
                }
            }
            if(listToString(testIfCorrect) == wordsBefore.get(0)){
                return null;
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

        // once the biggest family is picked, setup to 'reveal' the letters
        addToRevealed(maxSizedFamily, guess);

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

        return organizedWords.get(maxSizedFamily);
    }

    /**
     * Turns words into frequency tables based on if or where the guessed letter is
     * @param word word being checked
     * @param guess guessed letter
     * @return Frequency table of guess in letter
     */
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

    /**
     * Changes which letters are revealed to the player
     * @param charFreq the chosen frequency table on where the guessed letter should appear
     * @param guess guessed letter
     */
    private void addToRevealed(LinkedList<Character> charFreq, Character guess){
        for (int i = 0; i < charFreq.size(); i++) {
            if (charFreq.get(i) == '1'){
                lettersRevealed.set(i, guess);
            }
        }
    }

    /**
     * Turns a list into a string
     * @param list input list
     * @returnstring of list
     */
    private String listToString(LinkedList<Character> list){
        String output = "";
        for (Character character: list) {
            output += character;
        }
        return output;
    }

    /**
     * Returns how many guesses you have remaining
     * @return amount of remaining guesses
     */
    public int guessesRemain(){
        return guessAmount - incorrectGuesses;
    }

    /**
     * Checks if there are guesses remaining
     * @return if guesses remain
     */
    public boolean hasGuesses(){
        if(incorrectGuesses > guessAmount){
            return false;
        }
        return true;
    }

    /**
     * Returns the status of which letters have been 'found' and which ones haven't
     * '-' means it hasn't been found
     * @return a string representing which letters have been found
     */
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

    /**
     * Returns how many incorrect guesses it took to guess the word
     * @return amount of incorrect guesses chosen
     */
    public int getIncorrectGuesses(){
        return incorrectGuesses;
    }
}
