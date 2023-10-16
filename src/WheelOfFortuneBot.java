import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneBot {
//instance data members
    public String phrase;
    public StringBuilder hiddenPhrase = new StringBuilder();
    public StringBuilder previousGuesses = new StringBuilder();
    public int phraseLength;

    public static void main(String[] args) {
        WheelOfFortuneBot wheelOfFortune = new WheelOfFortuneBot();
        //My code start from here.
        int maxGuess = 20;
        String aToz;
        int longestLength;
        wheelOfFortune.randomPhrase();
        wheelOfFortune.generateHiddenPhrase(wheelOfFortune.phrase);
        System.out.println("Welcome to Wheel of Fortune.\n" +
                "This game is to guess the hidden letters from the asterisks shown below. You have up to "+
                maxGuess+" times of guessing.\nIf you can guess all the letters within "+ maxGuess+
                " times. You will win the game. Notes: All none letter character will not count.\n" +
                "Best luck for you. Thank you!!");
        System.out.println(wheelOfFortune.hiddenPhrase);
        //Guess bot input. The aToz in else block is unintelligent guess,
        // but it will play through the whole game.
        // All others are base the length of the phrase,
        // which letter has higher appearance rate in the front and less at the end.
        // These length strings comes
        // from http://www.datagenetics.com/blog/april12012/index.html
        longestLength = wheelOfFortune.longestWord(wheelOfFortune.phrase).length();
        if (longestLength <= 5){
            aToz = "aniesorltudpmhcbkgywfvjzxq";
        }else if (longestLength <= 12) {
            aToz = "eisntarolcpumdghybvfzkwxqj";
        } else if (longestLength <= 20) {
            aToz = "ioetrsanclphumydgbzvfkxjqw";
        }else{
            aToz = "abcdefghijklmnopqrstuvwxyz";//unintelligent guess from a to z in order.
            maxGuess = 26;
        }
        if (wheelOfFortune.processGuess(wheelOfFortune.phrase,wheelOfFortune,maxGuess,aToz)){
            System.out.println("Congratulation! YOU WIN!!!! :)");
        }else{
            System.out.println("Sorry,:( you use all of your chances.");
        }
        //System.out.println(longestLength); //test the longestLength
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

    }//This method is a void method. It randomly output a line from the phrase.txt and update the phrase variable for later use.
    public void generateHiddenPhrase(String phrase){

        //convert each char of a string to *
        for(int i = 0; i < phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))){ //check if all the characters in the object are letter.
                this.hiddenPhrase.append('*');// replace to * in the same location.
                this.phraseLength++;
            }else{
                this.hiddenPhrase.append(phrase.charAt(i));
            }
        }
    }// This method is a void method. It appends the asterisks for all the letter characters, and it will keep the every thing else as the same. Therefore, the player know the length and format.
    public char getGuess (String aToz, int start, int end){
        String guessString = aToz.substring(start,end);
        return guessString.charAt(0);
    }//Since user input have no data member, this method will return a value for later use. Also, it will check if the user accidentally input more than one character. it returns the use guess.
    public boolean processGuess(String phrase, WheelOfFortuneBot wheelOfFortune, int maxGuess, String aToz){
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
                guess = wheelOfFortune.getGuess(aToz, numGuess, maxGuess);
                System.out.println(guess);
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
    } //This method will return a boolean value.
    // if all the letters are guessed right,
    // it will true. otherwise, it will return a false value.

    public String longestWord(String phrase){
        StringBuilder currentWord = new StringBuilder();
        StringBuilder longestWord = new StringBuilder();
        char character;

        for(int i = 0; i < phrase.length();i++){
            character = phrase.charAt(i);

            if (!Character.isLetter(character)){ // not letter = true.

                if(currentWord.length() > longestWord.length()){
                    longestWord.setLength(0); //reset longestWord before append new value.
                    longestWord.append(currentWord); // append new value to the longestWord
                }
                currentWord.setLength(0); //reset the currentWord for next word.
                // if the currentWord has the same length as longestWord
                // that need to rest. so it won't affect the next append.
            }else{
                currentWord.append(character);//append a letter before not letter.
            }
        }
        return longestWord.toString();
    }  //This method returned the longest word from the phrase for bot to check the most frequent letter to specific length of the word.
}
