package mazegame.labyrinth.generator;

import backend.academy.mazegame.labyrinth.generator.MazeGenerator;
import backend.academy.mazegame.labyrinth.generator.impl.SimpleMazeGenerator;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.representation.impl.SimpleMazeRepresentation;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleMazeGeneratorTest {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void generateMazeTest() {
        MazeGenerator generator = new SimpleMazeGenerator();
        SimpleMazeRepresentation representation = new SimpleMazeRepresentation();
        final int MIN_VALID_LENGTH = 3;
        ArrayList<Maze> results = new ArrayList<>();

        for (int i = MIN_VALID_LENGTH; i < 100; i++) {
            results.add(generator.generateMaze(i));
        }

        results.forEach(m -> {
            assertNotNull(m);
            assertNotNull(m.maze());
        });

        for (int i = -10; i < MIN_VALID_LENGTH; i++) {
            int finalI = i;
            assertThrows(IllegalArgumentException.class, () -> generator.generateMaze(finalI));
        }

    }
}
