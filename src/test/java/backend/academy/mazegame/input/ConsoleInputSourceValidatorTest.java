package backend.academy.mazegame.input;

import backend.academy.mazegame.input.impl.ConsoleInputValidator;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.parameters.GameParameters;
import backend.academy.mazegame.maze.parameters.GameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleInputSourceValidatorTest {
    private final Maze defaultMaze = new Maze(new char[][] {
        {' ', '█', ' '},
        {'█', '█', ' '},
        {' ', ' ', ' '}
    });

    @ParameterizedTest
    @DisplayName("Valid input with parameters")
    @CsvSource({"1,CHOOSE_MAIN_MENU_OPTION", "1,CHANGE_PATH_ALGORITHM", "2;0 0;2,FIND_PATH"})
    public void inputIsValidTest(String input, GameState gameState) {
        ConsoleInputValidator validator = new ConsoleInputValidator();
        GameParameters parameters = new GameParameters();
        parameters.setMaze(defaultMaze);
        parameters.setState(gameState);

        boolean result = validator.inputIsValid(input, parameters);

        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Invalid input with parameters")
    @CsvSource({"15,CHOOSE_MAIN_MENU_OPTION", "-1,CHANGE_PATH_ALGORITHM", "1;1a2;3,FIND_PATH", "1;0 0;1,FIND_PATH"})
    public void inputIsValidTestWithInvalidInput(String input, GameState gameState) {
        ConsoleInputValidator validator = new ConsoleInputValidator();
        GameParameters parameters = new GameParameters();
        parameters.setMaze(defaultMaze);
        parameters.setState(gameState);

        boolean result = validator.inputIsValid(input, parameters);

        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Valid input")
    @ValueSource(strings = {"1", "24", "1555", "9"})
    public void integerInputIsValidTest(String validInput) {
        ConsoleInputValidator validator = new ConsoleInputValidator();

        boolean result = validator.integerInputIsValid(validInput);

        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Invalid input")
    @ValueSource(strings = {"-12", "asd", "A123", "1A2", "-a"})
    public void integerInputIsInvalidTest(String invalidInput) {
        ConsoleInputValidator validator = new ConsoleInputValidator();

        boolean result = validator.integerInputIsValid(invalidInput);

        assertFalse(result);
    }
}
