import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.lang.Character;
import java.lang.StringBuilder;
import java.util.Scanner;

public class WheelOfFortuneMethods {
        // Get a random phrase from the list
        public static String randomPhrase(){
            List<String> phraseList = null;
            // Get the phrase from a file of phrases
            try {
                phraseList = Files.readAllLines(Paths.get("phrases.txt"));
            } catch (IOException e) {
                System.out.println(e);
            }
            Random rand = new Random();
            int r = rand.nextInt(phraseList.size());

            return phraseList.get(r);
        }
        public static String generateHiddenPhrase(String phrase){
            StringBuilder asterisks = new StringBuilder(phrase);//create an object asterisks which pass all phrase in the location
            //convert each char of a string to *
            for(int i = 0; i < asterisks.length(); i++){
                if(Character.isLetter(asterisks.charAt(i))){ //check if all the characters in the object are letter.
                    asterisks.setCharAt(i,'*');// replace to * in the same location.
                }
            }
            return asterisks.toString();
        }
        public static char getGuess(){
            Scanner scanner = new Scanner(System.in);
            return  scanner.nextLine().charAt(0);
        }
        public static Boolean processGuess(String phrase, int numGuess, int maxGuess,StringBuilder asterisks, StringBuilder alreadyInput){
            int checkUpperCase;
            int checkLowerCase;
            boolean outOfGuess = false;
            boolean match = false;
            int letterExist;
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
                    char guess = getGuess(); //

                    //All character values that has been guessed will store in alreadyInput
                    if(alreadyInput.indexOf(String.valueOf(Character.toLowerCase(guess)))== -1){
                        alreadyInput.append(guess); //append

                    }
                    else { // in else, the character guessed already.
                        System.out.println(guess + " is guessed previously. Please try again.");
                        System.out.println(asterisks);
                        System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));
                        continue;
                    }
                /*
                This is the for guess the letters.
                */
                    if (!Character.isLetter(guess)) {
                        System.out.println(guess+" is not an letter. Please input one letter.");
                    }//
                    else{
                        // Check the guess for both uppercase and lowercase. upper first and lower after
                        if(Character.isUpperCase(guess)){ //check if uppercase
                            checkUpperCase  = phrase.indexOf(guess);
                            while(checkUpperCase >= 0){
                                asterisks.setCharAt(checkUpperCase,guess);
                                checkUpperCase = phrase.indexOf(guess,checkUpperCase+1);
                                letterExist++;
                            }
                            oppGuess = Character.toLowerCase(guess);
                            checkLowerCase = phrase.indexOf(oppGuess);

                            while(checkLowerCase >= 0){
                                asterisks.setCharAt(checkLowerCase,oppGuess);
                                checkLowerCase = phrase.indexOf(oppGuess,checkLowerCase+1);
                                letterExist++;
                            }
                        }
                        else{  //otherwise lowercase.
                            checkLowerCase = phrase.indexOf(guess);
                            while(checkLowerCase >= 0){
                                asterisks.setCharAt(checkLowerCase,guess);
                                checkLowerCase = phrase.indexOf(guess,checkLowerCase+1);
                                letterExist++;
                            }
                            //toUpperCase
                            oppGuess = Character.toUpperCase(guess);
                            checkUpperCase = phrase.indexOf(oppGuess);
                            while(checkUpperCase >= 0){
                                asterisks.setCharAt(checkUpperCase,oppGuess);
                                checkUpperCase = phrase.indexOf(oppGuess,checkUpperCase+1);
                                letterExist++;
                            }
                        }// Check the guess for both uppercase and lowercase. lowercase and uppercase
                        if(letterExist == 0){
                            System.out.println("Opps!! "+guess +" is not on the list!");
                            missGuess++;
                        }else{
                            System.out.println("There is a(n) "+ guess+".\n");
                        }

                        numGuess++;
                        System.out.println("Count guesses: "+numGuess+". Miss guesses: "+missGuess +". Guesses left: "+(maxGuess-numGuess));

                        System.out.println(asterisks);

                    }
                    //Execute this if phrase = asterisks.
                    if (phrase.equals(asterisks.toString())){// asterisks is an object. convert to string for comparison.
                        match = true;
                        break;
                    }
                }

            }


        return match;
        }
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            String phrase = randomPhrase();
            int numGuess = 0;
            int maxGuess = 15;

            StringBuilder alreadyInput = new StringBuilder();
            System.out.println("Welcome to Wheel of Fortune.\n" +
                    "This game is to guess the hidden letters from the asterisks shown below. You have up to "+
                    maxGuess+" times of guessing.\nIf you can guess all the letters within "+ maxGuess+
                    " times. You will win the game. Notes: All none letter character will not count.\n" +
                    "Best luck for you. Thank you!!");
            System.out.println(generateHiddenPhrase(phrase));
            StringBuilder asterisks = new StringBuilder(generateHiddenPhrase(phrase));
            boolean match = processGuess(phrase,numGuess,maxGuess,asterisks,alreadyInput);
            if (match){
                System.out.println("Congratulation! YOU WIN!!!! :)");
            }else{
                System.out.println("Sorry,:( you use all of your chances.");
            }


    }
}
