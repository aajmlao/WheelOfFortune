import java.io.IOException;

public interface WheelOfFortunePlayer {
    char nextGuess();
    String playerId() throws IOException;
    void reset();
}
