import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortune extends Game{
    private String phrase;
    private StringBuilder hiddenPhrase = new StringBuilder();
    private StringBuilder previousGuesses = new StringBuilder();
    List<String> phraseList  =readPhrases();
    private int numGuess = 0;
    private int missGuess = 0;
    protected abstract char getGuess();

    protected List<String> readPhrases(){

        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        return phraseList;
    }

    protected String randomPhrase(){
        Random rand = new Random();
        int r = rand.nextInt(phraseList.size());
        this.phrase = phraseList.get(r);
        phraseList.remove(r);
        return phrase;
    }

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

    protected String getHiddenPhrases(){
        return hiddenPhrase.toString();
    }

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

    protected void setNumGuess(int numGuess){
        this.numGuess = numGuess;
    }

    protected int getNumGuess(){
        return numGuess;
    }

    protected void setMissGuess(int missGuess){
        this.missGuess = missGuess;
    }

    protected int getMissGuess(){
        return missGuess;
    }

    protected void resetPreviousGuesses(){
        previousGuesses.setLength(0);
    }

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

}
