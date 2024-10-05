package backend.academy.mazegame.labyrinth.navigation;

import backend.academy.mazegame.labyrinth.navigation.impl.SimpleMazePathFinder;
import java.util.Map;

public enum NavigationAlgorithms {
    SIMPLE_PATH_FINDER(1, new SimpleMazePathFinder(), "Поиск в ширину");

    public final PathFinder value;
    public final String description;
    public final int correspondingInput;

    NavigationAlgorithms(int input, PathFinder pathFinder, String desc) {
        value = pathFinder;
        description = desc;
        correspondingInput = input;
    }

    public static PathFinder getAlgorithm(int input) {
        //TODO: add new realization
        Map<Integer, PathFinder> type = Map.of(
            1, SIMPLE_PATH_FINDER.value
        );
        return type.get(input);
    }

    public static String getAllNavigationAlgorithmsWithDescriptions() {
        StringBuilder result = new StringBuilder();
        for (NavigationAlgorithms algorithm : NavigationAlgorithms.values()) {
            result.append(algorithm.correspondingInput).append(". ").append(algorithm.description);
        }

        return result.toString();
    }

}
