package mazegame.input;

import backend.academy.mazegame.input.impl.ConsoleInputProcessor;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.parameters.GameParameters;
import backend.academy.mazegame.parameters.GameState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleInputProcessorTest {
    private final Maze defaultMaze = new Maze(new char[][] {
        {' ', '█', ' '},
        {'█', '█', ' '},
        {' ', ' ', ' '}
    });

    @Test
    public void parsePointsTest() {
        ConsoleInputProcessor processor = new ConsoleInputProcessor();
        String point1String = "0;0 2;2";
        Point point1Start = new Point(0, 0);
        Point point1End = new Point(2, 2);

        String point2String = "2;0 2;2";
        Point point2Start = new Point(2, 0);
        Point point2End = new Point(2, 2);

        String point3String = "0;0 0;0";
        Point point3Start = new Point(0, 0);
        Point point3End = new Point(0, 0);

        Point[] result1 = processor.parsePoints(point1String);
        Point[] result2 = processor.parsePoints(point2String);
        Point[] result3 = processor.parsePoints(point3String);

        assertEquals(point1Start, result1[0]);
        assertEquals(point1End, result1[1]);
        assertEquals(point2Start, result2[0]);
        assertEquals(point2End, result2[1]);
        assertEquals(point3Start, result3[0]);
        assertEquals(point3End, result3[1]);
    }

    @ParameterizedTest
    @CsvSource({"1,CHOOSE_MAIN_MENU_OPTION,CHANGE_GENERATING_ALGORITHM",
        "1,CHANGE_PATH_ALGORITHM,CHOOSE_MAIN_MENU_OPTION",
        "5,CHOOSE_MAIN_MENU_OPTION,FINISH"})
    public void processInputTest(String input, GameState gameState, GameState expectedGameState) {
        ConsoleInputProcessor processor = new ConsoleInputProcessor();
        GameParameters parameters = new GameParameters();
        parameters.setMaze(defaultMaze);
        parameters.setState(gameState);

        processor.processInput(input, parameters);

        assertEquals(expectedGameState, parameters.getState());
    }
}
