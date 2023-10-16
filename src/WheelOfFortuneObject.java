import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.lang.Character;
import java.lang.StringBuilder;
import java.util.Scanner;

public class WheelOfFortuneObject {
    //instance data members.
    public String phrase;
    public StringBuilder hiddenPhrase = new StringBuilder();
    public StringBuilder previousGuesses = new StringBuilder();
    int maxGuess = 15;// static
    public static void main(String[] args) {
        WheelOfFortuneObject wheelOfFortune = new WheelOfFortuneObject();
        //My code start from here.
        int maxGuess = 15;// static
        wheelOfFortune.randomPhrase();
        wheelOfFortune.generateHiddenPhrase(wheelOfFortune.phrase);//wheelOfFortune.phrase is sending two thing in, which the object and the phrase, but the object is include the phrase already.
        System.out.println("Welcome to Wheel of Fortune.\n" +
                "This game is to guess the hidden letters from the asterisks shown below. You have up to "+
                maxGuess+" times of guessing.\nIf you can guess all the letters within "+ maxGuess+
                " times. You will win the game. Notes: All none letter character will not count.\n" +
                "Best luck for you. Thank you!!");
        System.out.println(wheelOfFortune.hiddenPhrase);
        // one guess at the time. //wheelOfFortune.phrase is sending two thing in, which the object and the phrase, but the object is include the phrase already.
        if (wheelOfFortune.processGuess(wheelOfFortune.phrase,wheelOfFortune,maxGuess)){
            System.out.println("Congratulation! YOU WIN!!!! :)");
        }else{
            System.out.println("Sorry,:( you use all of your chances.");
        }

    }
    public void randomPhrase(){
        List<String> phraseList = null;

        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        //System.out.println(phraseList.size());// return an int of the size of the letter.
        int r = rand.nextInt(phraseList.size());
        this.phrase = phraseList.get(r); //create data member "phrase" as required.
        //System.out.println(phrase);

    } //This method is a void method. It randomly output a line from the phrase.txt and update the phrase variable for later use.
    public void generateHiddenPhrase(String phrase){

        //convert each char of a string to *
        for(int i = 0; i < phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))){ //check if all the characters in the object are letter.
                this.hiddenPhrase.append('*');// replace to * in the same location.
            }else{
                this.hiddenPhrase.append(phrase.charAt(i));
            }
        }
    } // This method is a void method. It appends the asterisks for all the letter characters, and it will keep the every thing else as the same. Therefore, the player know the length and format.
    //have getGuess handle the guessing functions.
    public char getGuess(Scanner scanner){
        String guessString = null;
        boolean tooLong = true;
        while(tooLong){
            guessString = scanner.nextLine();
            if(guessString.length()>1){
                System.out.println("Only one character at a time. Try a again.");
                System.out.print("Please enter your guess: ");
            }// if input length is longer than 1 character.
            else{
                tooLong = false;
            }
        }
        return guessString.charAt(0);
    } //Since user input have no data member, this method will return a value for later use. Also, it will check if the user accidentally input more than one character. it returns the use guess.
    public boolean processGuess(String phrase, WheelOfFortuneObject wheelOfFortune, int maxGuess){//one each
        //initialization
        boolean outOfGuess = false;
        int numGuess = 0;
        int letterExist;
        Scanner scanner = new Scanner(System.in);
        char guess;
        int checkUpperCase;
        int checkLowerCase;
        boolean match = false;
        char oppGuess;
        int missGuess = 0;

        while(!outOfGuess){ //not true (!true) = false
            // the condition of using all the number of Guess
            if (maxGuess == numGuess){
                outOfGuess = true; //exit the while loop while outOfGuess is true.
            }
            else{
                // enter guess each time.
                letterExist = 0; //Set letterExist to nothing for each run.
                System.out.print("Please enter your guess: ");
                // the way to input a char.
                guess = wheelOfFortune.getGuess(scanner);
                //All character values that has been guessed will store in alreadyInput
                if(previousGuesses.indexOf(String.valueOf(Character.toLowerCase(guess)))== -1){
                    previousGuesses.append(guess); //append

                }
                else { // in else, the character guessed already.
                    System.out.println(guess + " is guessed previously. Please try again.");
                    System.out.println(hiddenPhrase);
                    System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));
                    continue;
                }

                if (!Character.isLetter(guess)) {
                    System.out.println(guess+" is not an letter. Please input one letter.");
                }//
                else{
                    // Check the guess for both uppercase and lowercase. upper first and lower after
                    if(Character.isUpperCase(guess)){ //check if uppercase
                        checkUpperCase  = phrase.indexOf(guess);
                        //avoid have the same code more than one time.
                        while(checkUpperCase >= 0){
                            hiddenPhrase.setCharAt(checkUpperCase,guess);
                            checkUpperCase = phrase.indexOf(guess,checkUpperCase+1);
                            letterExist++;
                        }
                        oppGuess = Character.toLowerCase(guess);
                        checkLowerCase = phrase.indexOf(oppGuess);

                        while(checkLowerCase >= 0){
                            hiddenPhrase.setCharAt(checkLowerCase,oppGuess);
                            checkLowerCase = phrase.indexOf(oppGuess,checkLowerCase+1);
                            letterExist++;
                        }
                    }
                    else{  //otherwise lowercase.
                        checkLowerCase = phrase.indexOf(guess);
                        while(checkLowerCase >= 0){
                            hiddenPhrase.setCharAt(checkLowerCase,guess);
                            checkLowerCase = phrase.indexOf(guess,checkLowerCase+1);
                            letterExist++;
                        }
                        //toUpperCase
                        oppGuess = Character.toUpperCase(guess);
                        checkUpperCase = phrase.indexOf(oppGuess);
                        while(checkUpperCase >= 0){
                            hiddenPhrase.setCharAt(checkUpperCase,oppGuess);
                            checkUpperCase = phrase.indexOf(oppGuess,checkUpperCase+1);
                            letterExist++;
                        }
                    }// Check the guess for both uppercase and lowercase. lowercase and uppercase
                    if(letterExist == 0){
                        System.out.println("Opps!! "+guess +" is not on the list!");
                        missGuess++;
                    }else{
                        System.out.println("There is a(n) "+ guess+"!!!!");
                    }

                    numGuess++;
                    System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));

                    System.out.println(hiddenPhrase);

                }
                //Execute this if phrase = asterisks.
                if (phrase.equals(hiddenPhrase.toString())){// hiddenPhrase is an object. convert to string for comparison.
                    match = true;
                    break;
                }
            }

        }
        return match;
    } //This method will return a boolean value. if all the letters are guessed right, it will true. otherwise, it will return a false value.
}