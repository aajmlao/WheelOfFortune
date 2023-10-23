import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WheelOfFortuneAIGame extends WheelOfFortune implements WheelOfFortunePlayer{
    private String player;
    private int maxGuess = 15;
    private int phraseLength;
    private int index = 0;
    private int indexOfPhrase = 0;
    private  int indexOfPlayer = 0;
    List<String> players = new ArrayList<>();

    public WheelOfFortuneAIGame(){
        this.player = "AA8687";
    }
    public WheelOfFortuneAIGame(String player){
        this.player = player;
    }
    public WheelOfFortuneAIGame(List<String> players){
       this.players = players;

    }

    @Override
    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        boolean isContinue = true;
        int size = phraseList.size();
        int j = 0;

        for (int i = 0; i < players.size();i++){
            indexOfPlayer = i;
            player = playerId();

            while (isContinue){
                j++;
                allGamesRecord.add(play());
                if (size > j) {
                    isContinue = playNext();
                } else {
                    isContinue = false;
                }
                reset();
            }
            isContinue = true;
            super.readPhrases();
            j=0;
        }
        return allGamesRecord;
    }

    @Override
    public boolean playNext() {
        int size = phraseList.size();
        boolean isFinish = true;
        if (size == indexOfPhrase){
            isFinish = false;
        }
        return isFinish;
    }

    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        boolean outOfRange = false;
        boolean isWin = false;
        String phrase = super.randomPhrase();
        super.generateHiddenPhrase(phrase);
        phraseLength = phrase.length();

        gameRecord.playerID = player;

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
    public char nextGuess() {
        String botGuess;
        if (phraseLength <= 5){
            botGuess = "aniesorltudpmhcbkgywfvjzxq";
        }else if (phraseLength <= 12) {
            botGuess = "eisntarolcpfumvdghybzkwxqj";
        } else {
            botGuess = "ioetrsanclphumydgbzvfkxjqw";
        }
        return botGuess.charAt(index);
    }

    @Override
    public String playerId(){
        return players.get(indexOfPlayer);
    }

    @Override
    public void reset() {
        setNumGuess(0);
        setMissGuess(0);
        setMaxGuess();
        resetPreviousGuesses();
        index=0;
    }

    private void setMaxGuess(){
        this.maxGuess = 15;
    }

    @Override
    protected char getGuess() {
        char nextGuess = nextGuess();
        index++;
        return nextGuess;
    }

    public static void main(String[] args){
        List<String> playerList = new ArrayList<>();
        try {
            playerList = Files.readAllLines(Paths.get("players.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        WheelOfFortuneAIGame wf = new WheelOfFortuneAIGame(playerList);
        AllGamesRecord record = wf.playAll();

        System.out.println("This is an average: "+record.average("KK8687"));
        System.out.println("This is an average: "+record.average("JJ8687"));
        System.out.println("This is an average: "+record.average("AA8687"));
        for (GameRecord gamesRecord: record.highGameList("AA8687",3)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }

    }
}
