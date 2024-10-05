package backend.academy.mazegame.labyrinth.generator;

import backend.academy.mazegame.labyrinth.generator.impl.SimpleMazeGenerator;
import java.util.Map;

public enum GeneratingAlgorithms {
    SIMPLE_MAZE_GENERATOR(1, new SimpleMazeGenerator(), "Простой алгоритм (не очень интересные лабиринты)");

    public final MazeGenerator value;
    public final String description;
    public final int correspondingInput;

    GeneratingAlgorithms(int input, MazeGenerator mazeGenerator, String desc) {
        value = mazeGenerator;
        description = desc;
        correspondingInput = input;
    }

    public static MazeGenerator getAlgorithm(int input) {
        //TODO: add new ralization
        Map<Integer, MazeGenerator> type = Map.of(
            1, SIMPLE_MAZE_GENERATOR.value
        );
        return type.get(input);
    }

    public static String getAllGeneratingAlgorithmsWithDescriptions() {
        StringBuilder result = new StringBuilder();
        for (GeneratingAlgorithms algorithm : GeneratingAlgorithms.values()) {
            result.append(algorithm.correspondingInput).append(". ").append(algorithm.description);
        }

        return result.toString();
    }
}
