import java.util.ArrayList;
import java.util.Collections;

public class AllGamesRecord {
    ArrayList<GameRecord> allGameRecord = new ArrayList<>();

    public void add(GameRecord gameRecord){
        allGameRecord.add(gameRecord);
    }

    public float average(){
        float sum = 0;
        for (GameRecord gameRecord : allGameRecord) {
            sum += gameRecord.score;
        }
        return sum/allGameRecord.size();
    }

    public float average(String playerID){
        float sum = 0;
        int i = 0;
        for (GameRecord gameRecord: allGameRecord){
            if(gameRecord.playerID.equals(playerID)){
                sum += gameRecord.score;
                i++;
            }
        }
        return sum/i;
    }

    public ArrayList<GameRecord> highGameList(int n){
        Collections.sort(allGameRecord);
        ArrayList<GameRecord> highGameRecord = new ArrayList<>();
        for(int i = allGameRecord.size()-1; (allGameRecord.size() - n) <= i; i--){
            highGameRecord.add(allGameRecord.get(i));
        }
        return highGameRecord;
    }

    public ArrayList<GameRecord> highGameList(String playerID,int n){
        Collections.sort(allGameRecord);
        ArrayList<GameRecord> playerHighGameRecord = new ArrayList<>();
        ArrayList<GameRecord> newPlayerHighGameRecord = new ArrayList<>();
        for (int i = 0; i < allGameRecord.size();i++){

            if (this.allGameRecord.get(i).playerID.equals(playerID)){
                playerHighGameRecord.add(allGameRecord.get(i));
            }
        }

        for (int i = playerHighGameRecord.size()-1; playerHighGameRecord.size()-n <= i ;i--){
            newPlayerHighGameRecord.add(playerHighGameRecord.get(i));
        }
        return newPlayerHighGameRecord;
    }

}
