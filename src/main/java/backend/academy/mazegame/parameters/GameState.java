package backend.academy.mazegame.parameters;

import java.util.Map;

public enum GameState {
    CHOOSE_MAIN_MENU_OPTION(0),
    INVALID_INPUT(-1),

    //Main Menu Options, that require input
    CHANGE_GENERATING_ALGORITHM(1),
    CHANGE_PATH_ALGORITHM(2),
    GENERATE_MAZE(3),
    FIND_PATH(4),
    FINISH(5);

    public final int value;

    GameState(int i) {
        value = i;
    }

    public static GameState getInputType(int inp) {
        Map<Integer, GameState> type = Map.of(
            CHANGE_PATH_ALGORITHM.value, CHANGE_PATH_ALGORITHM,
            CHANGE_GENERATING_ALGORITHM.value, CHANGE_GENERATING_ALGORITHM,
            GENERATE_MAZE.value, GENERATE_MAZE,
            FIND_PATH.value, FIND_PATH,
            FINISH.value, FINISH
        );
        return type.get(inp);
    }
}
