package backend.academy.mazegame;

import backend.academy.mazegame.input.InputProcessor;
import backend.academy.mazegame.input.InputSource;
import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.input.impl.ConsoleInputProcessor;
import backend.academy.mazegame.input.impl.ConsoleInputValidator;
import backend.academy.mazegame.maze.generator.GeneratingAlgorithms;
import backend.academy.mazegame.maze.parameters.GameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MazeGameRunnerTest {

    @Mock
    InputSource<String> mockInputSource;

    @Spy
    InputValidator<String> inputValidator = new ConsoleInputValidator();

    @Spy
    InputProcessor<String> inputProcessor = new ConsoleInputProcessor();

    @InjectMocks
    MazeGameRunner<String> mazeGameRunner;

    @Test
    @DisplayName("Testing a whole game session")
    public void gameSessionTest() {
        //generate, parameters, change generation algorithm, new algorithm, generate, parameters, end game
        when(mockInputSource.getNextInput()).thenReturn(
            "3", "4 4", "1", "2", "3", "5 10", "5"
        );

        mazeGameRunner.startGame();

        verify(inputProcessor).greetUser();
        assertEquals(mazeGameRunner.parameters.getGeneratorAlgorithm(),
            GeneratingAlgorithms.getAlgorithm(2));
        assertEquals(mazeGameRunner.parameters.getState(), GameState.FINISH);
    }
}
