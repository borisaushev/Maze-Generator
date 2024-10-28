package backend.academy.mazegame.maze.navigation;

import backend.academy.mazegame.maze.navigation.impl.BFSMazePathFinder;
import backend.academy.mazegame.maze.navigation.impl.DFSMazePathFinder;
import backend.academy.mazegame.maze.navigation.impl.DifferentLandscapeMazePathFinder;
import java.util.Map;

public enum NavigationAlgorithms {
    BFS_PATH_FINDER(1, new BFSMazePathFinder(), "Поиск в ширину"),
    DFS_PATH_FINDER(2, new DFSMazePathFinder(), "Поиск в глубину"),
    DIFFERENT_LANDSCAPE_PATH_FINDER(3, new DifferentLandscapeMazePathFinder(), "Поиск пути с учетом монеток и болот");

    public final PathFinder value;
    public final String description;
    public final int correspondingInput;

    NavigationAlgorithms(int input, PathFinder pathFinder, String desc) {
        value = pathFinder;
        description = desc;
        correspondingInput = input;
    }

    public static NavigationAlgorithms getDefaultAlgorithm() {
        return BFS_PATH_FINDER;
    }

    public static PathFinder getAlgorithm(int input) {
        Map<Integer, PathFinder> type = Map.of(
            BFS_PATH_FINDER.correspondingInput, BFS_PATH_FINDER.value,
            DFS_PATH_FINDER.correspondingInput, DFS_PATH_FINDER.value,
            DIFFERENT_LANDSCAPE_PATH_FINDER.correspondingInput, DIFFERENT_LANDSCAPE_PATH_FINDER.value
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
