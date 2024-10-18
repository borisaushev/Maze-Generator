package backend.academy.mazegame.representation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.representation.impl.SimpleMazeRepresentation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMazeRepresentationTest {
    @Test
    @DisplayName("Represent a simple 3x3 mazeMatrix")
    public void simpleMazeTest() {
        Maze maze = new Maze(new char[][] {
            {'█', ' ', '█'},
            {'█', ' ', ' '},
            {'█', ' ', '█'}
        });

        SimpleMazeRepresentation representation = new SimpleMazeRepresentation();

        String expected = """
               0 1 2\s
            0 |█| |█|
            1 |█| | |
            2 |█| |█|
            """;
        String result = representation.getMazeRepresentation(maze);

        assertEquals(expected, result);
    }
}
