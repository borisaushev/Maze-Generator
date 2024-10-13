package backend.academy.mazegame.labyrinth.navigation;

import backend.academy.mazegame.labyrinth.navigation.impl.BFSMazePathFinder;
import backend.academy.mazegame.labyrinth.navigation.impl.DFSMazePathFinder;
import java.util.Map;

public enum NavigationAlgorithms {
    BFS_PATH_FINDER(1, new BFSMazePathFinder(), "Поиск в ширину"),
    DFS_PATH_FINDER(2, new DFSMazePathFinder(), "Поиск в глубину");

    public final PathFinder value;
    public final String description;
    public final int correspondingInput;

    NavigationAlgorithms(int input, PathFinder pathFinder, String desc) {
        value = pathFinder;
        description = desc;
        correspondingInput = input;
    }

    public static PathFinder getAlgorithm(int input) {
        Map<Integer, PathFinder> type = Map.of(
            1, BFS_PATH_FINDER.value,
            2, DFS_PATH_FINDER.value
        );
        return type.get(input);
    }

    public static String getAllNavigationAlgorithmsWithDescriptions() {
        StringBuilder result = new StringBuilder();
        for (NavigationAlgorithms algorithm : NavigationAlgorithms.values()) {
            result.append(algorithm.correspondingInput).append(". ")
                .append(algorithm.description).append('\n');
        }
        return result.toString();
    }
}
