public abstract class Game {

    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        GameRecord gameRecord = new GameRecord();
        boolean isContinues;
        allGamesRecord.add(gameRecord);
        play();
        isContinues = playNext();
        return allGamesRecord;
    }
    public abstract boolean playNext();
    public abstract GameRecord play();
}

