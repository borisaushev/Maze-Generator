package mazegame;

import backend.academy.mazegame.MazeGameRunner;
import backend.academy.mazegame.input.InputProcessor;
import backend.academy.mazegame.input.InputSource;
import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.input.impl.ConsoleInputProcessor;
import backend.academy.mazegame.input.impl.ConsoleInputSource;
import backend.academy.mazegame.input.impl.ConsoleInputValidator;
import backend.academy.mazegame.labyrinth.generator.GeneratingAlgorithms;
import backend.academy.mazegame.parameters.GameState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeGameRunnerTest {
    @Test
    public void gameSessionTest() {
        InputSource<String> mockInputSource = Mockito.mock(ConsoleInputSource.class);
        //generate, parameters, change generation algorithm, new algorithm, generate, parameters, end game
        Mockito.when(mockInputSource.getNextInput()).thenReturn(
            "3", "4 4", "1", "2", "3", "5 10", "5"
        );
        InputValidator<String> inputValidator = new ConsoleInputValidator();
        InputProcessor<String> inputProcessor = new ConsoleInputProcessor();
        MazeGameRunner<String> mazeGameRunner = new MazeGameRunner<>(mockInputSource, inputValidator, inputProcessor);

        mazeGameRunner.startGame();

        assertThat(mazeGameRunner.parameters.getGeneratorAlgorithm())
            .isEqualTo(GeneratingAlgorithms.getAlgorithm(2));
        assertThat(mazeGameRunner.parameters.getState()).isEqualTo(GameState.FINISH);
    }
}
