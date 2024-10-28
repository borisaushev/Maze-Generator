package backend.academy.mazegame.maze.generator;

import backend.academy.mazegame.maze.generator.impl.DifferentLandscapeMazeGenerator;
import backend.academy.mazegame.maze.generator.impl.PrimsMazeGenerator;
import backend.academy.mazegame.maze.generator.impl.SimpleMazeGenerator;
import java.util.Map;

public enum GeneratingAlgorithms {
    PRIM_MAZE_GENERATOR(1, new PrimsMazeGenerator(), "Алгоритм Прима (красивые и интересные лабиринты)"),
    SIMPLE_MAZE_GENERATOR(2, new SimpleMazeGenerator(), "Простой алгоритм (не очень интересные лабиринты)"),
    DIFFERENT_LANDSCAPE_MAZE_GENERATOR(3, new DifferentLandscapeMazeGenerator(),
        "Алгоритм генерирующий лабиринты с монетками и болотами");

    public final MazeGenerator value;
    public final String description;
    public final int correspondingInput;

    GeneratingAlgorithms(int input, MazeGenerator mazeGenerator, String desc) {
        value = mazeGenerator;
        description = desc;
        correspondingInput = input;
    }

    public static GeneratingAlgorithms getDefaultAlgorithm() {
        return PRIM_MAZE_GENERATOR;
    }

    public static MazeGenerator getAlgorithm(int input) {
        Map<Integer, MazeGenerator> type = Map.of(
            SIMPLE_MAZE_GENERATOR.correspondingInput, SIMPLE_MAZE_GENERATOR.value,
            PRIM_MAZE_GENERATOR.correspondingInput, PRIM_MAZE_GENERATOR.value,
            DIFFERENT_LANDSCAPE_MAZE_GENERATOR.correspondingInput, DIFFERENT_LANDSCAPE_MAZE_GENERATOR.value
        );
        return type.get(input);
    }

    public static String getAllGeneratingAlgorithmsWithDescriptions() {
        StringBuilder result = new StringBuilder();
        for (GeneratingAlgorithms algorithm : GeneratingAlgorithms.values()) {
            result.append(algorithm.correspondingInput).append(". ")
                .append(algorithm.description).append('\n');
        }
        return result.toString();
    }
}
