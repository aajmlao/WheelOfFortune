import java.util.Scanner;

public class WheelOfFortunedUserGame extends WheelOfFortune{
    Scanner scanner = new Scanner(System.in);
    private int maxGuess = 15;
    String playerId = null;


    @Override
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        int size = super.phraseList.size();
        boolean isContinue = true;
        int index = 0;

        System.out.println("Enter your Player ID: ");
        playerId = scanner.next();

        while (isContinue) {
            index++;
            allGamesRecord.add(play());
            if (size > index) {
                isContinue = playNext();
            } else {
                isContinue = false;
            }
            reset();
        }
        return allGamesRecord;
    }

    @Override
    public boolean playNext() {
        System.out.println("Do you want to continue?(y/n)");
        Scanner scanner = new Scanner(System.in);
        boolean contue = false;
        boolean loop = true;
        char chr;
        while(loop){
            chr = scanner.nextLine().charAt(0);
            if(chr =='y'||chr=='Y'){
                contue = true;
                loop = false;
            } else if (chr=='n'||chr=='N') {
                loop = false;
            } else{
                System.out.println("Invalid Input. Please Enter y or n: ");
            }
        }
        return contue;
    }

    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        boolean outOfRange = false;
        boolean isWin = false;
        String phrase = super.randomPhrase();
        super.generateHiddenPhrase(phrase);

        gameRecord.playerID = playerId;

        System.out.println(super.getHiddenPhrases());
        while(!outOfRange){
            char ch = getGuess();
            super.processGuess(ch, maxGuess);
            if(phrase.equals(super.getHiddenPhrases())){
                outOfRange = true;
                isWin = true;
            } else if (super.getNumGuess() == maxGuess) {
               break;
            }
        }
        if (isWin){
            gameRecord.score++;
        }
        System.out.println(gameRecord.playerID +" got "+gameRecord.score+ " point.");
        return gameRecord;
    }
    @Override
    protected char getGuess() {
        String guessString = null;
        boolean tooLong = true;
        Scanner scanner = new Scanner(System.in);
        while(tooLong){
            System.out.println("Please enter your guess: ");
            guessString = scanner.next();
            if(guessString.length()>1){
                System.out.println("Only one character at a time. Try a again.");
            }// if input length is longer than 1 character.
            else{
                tooLong = false;
            }
        }
        return guessString.charAt(0);
    }

    private void reset() {
        setNumGuess(0);
        setMissGuess(0);
        setMaxGuess();
        resetPreviousGuesses();
    }
    private void setMaxGuess(){
        this.maxGuess = 15;
    }

    public static void main(String[] args){
        WheelOfFortunedUserGame wf = new WheelOfFortunedUserGame();
        AllGamesRecord record = wf.playAll();

        System.out.println("This is an average: "+record.average());
        for (GameRecord gamesRecord: record.highGameList(3)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }
    }
}
