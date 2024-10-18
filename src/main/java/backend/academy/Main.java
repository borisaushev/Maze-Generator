package backend.academy;

import backend.academy.mazegame.ConsoleMazeGameStarter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        ConsoleMazeGameStarter gameStarter = new ConsoleMazeGameStarter();
        gameStarter.startConsoleMazeGame();
    }
}
