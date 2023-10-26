import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * abstract class WheelOfFortune is subclass of Game class that contains methods, such as
 *  abstract char getGuess(), readPhrases(), randomPhrase(),generateHiddenPhrase(String phrase),
 *  getHiddenPhrases(),processGuess(char guess, int maxGuess),setNumGuess(int numGuess),getNumGuess(),
 *  setMissGuess(int missGuess), getMissGuess(),resetPreviousGuesses(), getPhraseList(),
 *  checkUpperCase(char guess), checkLowerCase(char guess), toString(), and equals(Object o)
 */
public abstract class WheelOfFortune extends Game{
    private String phrase;
    private StringBuilder hiddenPhrase = new StringBuilder();
    private StringBuilder previousGuesses = new StringBuilder();
    private List<String> phraseList  =readPhrases();
    private int numGuess = 0;
    private int missGuess = 0;

    /**
     * requirement of getGuess()
     * @return char
     */
    protected abstract char getGuess();

    /**
     * read Phrases from file
     * @return phraseList
     */
    protected List<String> readPhrases(){
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        return phraseList;
    }

    /**
     * randomly get phrase
     * @return phrase
     */
    protected String randomPhrase(){
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size());
        this.phrase = phraseList.get(r);
        phraseList.remove(r);
        return phrase;
    }

    /**
     * generate Hidden Phrase
     * @param phrase
     */
    protected void generateHiddenPhrase(String phrase){
        hiddenPhrase.setLength(0);//reset StringBuilder
        //convert each char of a string to *
        for(int i = 0; i < phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))){ //check if all the characters in the object are letter.
                this.hiddenPhrase.append('*');// replace to * in the same location.
            }else{
                this.hiddenPhrase.append(phrase.charAt(i));
            }
        }
    }

    /**
     * get Hidden Phrase
     * @return String
     */
    protected String getHiddenPhrases(){
        return hiddenPhrase.toString();
    }

    /**
     * process input Guess one index by one index
     * @param guess
     * @param maxGuess
     */
    protected void processGuess(char guess, int maxGuess){//one each
        boolean isLetterExist;
        boolean guessed = false;

        //All character values that has been guessed will store in alreadyInput
        if(previousGuesses.indexOf(String.valueOf(Character.toLowerCase(guess)))== -1){
            previousGuesses.append(Character.toLowerCase(guess)); //append
        }
        else { // in else, the character guessed already.
            System.out.println(guess + " is guessed previously. Please try again.");
            System.out.println(hiddenPhrase);
            guessed = true;
            System.out.println("Guessed letters: "+previousGuesses);
            System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));
        }
        if (!Character.isLetter(guess)) {
            System.out.println(guess+" is not an letter. Please input one letter.");
        }
        else{
            if(!guessed){
                // Check the guess for both uppercase and lowercase. upper first and lower after
                if(Character.isUpperCase(guess)){ //check if uppercase
                    isLetterExist = checkUpperCase(guess);
                    if(isLetterExist){
                        checkLowerCase(Character.toLowerCase(guess));
                    }else{
                        isLetterExist = checkLowerCase(Character.toLowerCase(guess));
                    }
                }
                else{
                    isLetterExist = checkLowerCase(guess);
                    if(isLetterExist){
                        checkUpperCase(Character.toUpperCase(guess));
                    }else{
                        isLetterExist = checkUpperCase(Character.toUpperCase(guess));
                    }

                }// Check the guess for both uppercase and lowercase. lowercase and uppercase
                if(!isLetterExist){
                    System.out.println("Opps!! "+guess +" is not on the list!");
                    missGuess++;
                }else{
                    System.out.println("There is "+ guess+"!!!!");
                }
                numGuess++;

                System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));
                System.out.println("Guessed letters: "+ previousGuesses);
                System.out.println(hiddenPhrase);

            }
        }

    }

    /**
     * set number of Guess
     * @param numGuess
     */
    protected void setNumGuess(int numGuess){
        this.numGuess = numGuess;
    }

    /**
     * get numGuess
     * @return int
     */
    protected int getNumGuess(){
        return numGuess;
    }

    /**
     * set missed Guess
     * @param missGuess
     */
    protected void setMissGuess(int missGuess){
        this.missGuess = missGuess;
    }

    /**
     * get missGuess
     * @return int
     */
    protected int getMissGuess(){
        return missGuess;
    }

    /**
     * set PreviousGuesses
     */
    protected void resetPreviousGuesses(){
        previousGuesses.setLength(0);
    }

    /**
     * get phraseList
     * @return List<String>
     */
    protected List<String> getPhraseList(){
        return phraseList;
    }

    /**
     * helper method
     * @param guess
     * @return boolean
     */
    private boolean checkUpperCase(char guess){
        int checkUpperCase;
        boolean isLetterExist = false;
        checkUpperCase  = phrase.indexOf(guess);
        //avoid have the same code more than one time.
        while(checkUpperCase >= 0){
            hiddenPhrase.setCharAt(checkUpperCase,guess);
            checkUpperCase = phrase.indexOf(guess,checkUpperCase+1);
            isLetterExist = true;
        }
        return isLetterExist;
    }

    /**
     * helper method
     * @param guess
     * @return boolean
     */
    private boolean checkLowerCase(char guess){
        int checkLowerCase;
        boolean isLetterExist = false;
        checkLowerCase = phrase.indexOf(guess);
        while(checkLowerCase >= 0){
            hiddenPhrase.setCharAt(checkLowerCase,guess);
            checkLowerCase = phrase.indexOf(guess,checkLowerCase+1);
            isLetterExist = true;
        }
        return isLetterExist;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return "WheelOfFortune{" +
                "hiddenPhrase=" + hiddenPhrase +
                ", previousGuesses=" + previousGuesses +
                ", phraseList=" + phraseList +
                '}';
    }

    /**
     * compare object
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortune that = (WheelOfFortune) o;
        return Objects.equals(hiddenPhrase.toString(), that.hiddenPhrase.toString()) && Objects.equals(previousGuesses.toString(), that.previousGuesses.toString()) && Objects.equals(phraseList, that.phraseList);
    }
}
