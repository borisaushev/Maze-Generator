package backend.academy.mazegame.maze.generator;

import backend.academy.mazegame.maze.Maze;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeGeneratorTest {
    final static GeneratingAlgorithms[] algorithms = GeneratingAlgorithms.values();

    @ParameterizedTest
    @DisplayName("generating mazes")
    @FieldSource("algorithms")
    public void generateMazeValidParametersTest(GeneratingAlgorithms algorithm) {
        MazeGenerator generator = algorithm.value;
        ArrayList<Maze> results = new ArrayList<>();

        for (int height = Maze.MIN_MAZE_HEIGHT; height < 100; height++) {
            for (int width = Maze.MIN_MAZE_WIDTH; width < 100; width++) {
                results.add(generator.generateMaze(height, width));
            }
        }

        results.forEach(m -> {
            assertNotNull(m);
            assertNotNull(m.mazeMatrix());
        });
    }

    @ParameterizedTest
    @DisplayName("generating mazes with illegal parameters")
    @FieldSource("algorithms")
    public void generateMazeInvalidParametersTest(GeneratingAlgorithms algorithm) {
        MazeGenerator generator = algorithm.value;

        for (int height = -10; height < Maze.MIN_MAZE_HEIGHT; height++) {
            for (int width = -10; width < Maze.MIN_MAZE_WIDTH; width++) {
                int finalHeight = height;
                int finalWidth = width;
                assertThrows(IllegalArgumentException.class,
                    () -> generator.generateMaze(finalHeight, finalWidth));
            }
        }
    }
}
