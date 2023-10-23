public class GameRecord implements Comparable<GameRecord>{
    int score;
    String playerID;

    @Override
    public int compareTo(GameRecord o) {
        return Integer.compare(this.score, o.score);
    }
}
