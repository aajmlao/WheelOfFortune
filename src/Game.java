public abstract class Game {

    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        boolean isContinue = true;

        while (isContinue) {
            allGamesRecord.add(play());
            isContinue = playNext();
        }
        return allGamesRecord;
    }
    public abstract boolean playNext();
    public abstract GameRecord play();
}

