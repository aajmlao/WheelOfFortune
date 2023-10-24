import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortune implements WheelOfFortunePlayer{
    private int maxGuess = 15;
    private int index = 0;
    private int indexOfPlayer = 0;
    List<String> players = new ArrayList<>();

    public WheelOfFortuneAIGame(){
        players.add("AA8687");
    }
    public WheelOfFortuneAIGame(String player){
        players.add(player);
    }
    public WheelOfFortuneAIGame(List<String> players){
       this.players = players;

    }

    @Override
    public boolean playNext() {
        int sizeOfPhrase = phraseList.size(); // keep subtracting.
        int sizeOfPlayers = players.size()-1; // there were a player processed already.
        int indexOfPhrase = 0;
        boolean isFinish = true;

        if (sizeOfPhrase == indexOfPhrase){
            if(sizeOfPlayers == indexOfPlayer){
                isFinish = false;
            }else{
                super.readPhrases();
                indexOfPlayer++;
            }
        }
        reset();
        return isFinish;
    }

    @Override
    public GameRecord play() {
        GameRecord gameRecord = new GameRecord();
        boolean outOfRange = false;
        boolean isWin = false;
        String phrase = super.randomPhrase();
        super.generateHiddenPhrase(phrase);
        gameRecord.playerID = playerId();
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
        int phraseLength = phraseList.size();
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

        System.out.println(record.average());
        System.out.println("This is an average: "+record.average("KK8687"));
        System.out.println("This is an average: "+record.average("JJ8687"));
        System.out.println("This is an average: "+record.average("AA8687"));
        for (GameRecord gamesRecord: record.highGameList(3)){
            System.out.println(gamesRecord.playerID);
            System.out.println(gamesRecord.score);
        }

    }
}
