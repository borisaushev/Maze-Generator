package backend.academy.mazegame;

import backend.academy.mazegame.input.InputProcessor;
import backend.academy.mazegame.input.InputSource;
import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.labyrinth.generator.GeneratingAlgorithms;
import backend.academy.mazegame.labyrinth.navigation.NavigationAlgorithms;
import backend.academy.mazegame.parameters.GameParameters;
import backend.academy.mazegame.parameters.GameState;

public class MazeGameRunner<T> {
    public final GameParameters parameters;
    public final InputProcessor<T> inputProcessor;
    public final InputValidator<T> inputValidator;
    public final InputSource<T> inputSource;

    public MazeGameRunner(
        InputSource<T> source,
        InputValidator<T> validator,
        InputProcessor<T> processor
    ) {
        parameters = new GameParameters();
        parameters.setPathAlgorithm(NavigationAlgorithms.BFS_PATH_FINDER.value);
        parameters.setGeneratorAlgorithm(GeneratingAlgorithms.PRIM_MAZE_GENERATOR.value);
        parameters.setState(GameState.CHOOSE_MAIN_MENU_OPTION);
        inputSource = source;
        inputValidator = validator;
        inputProcessor = processor;
    }

    public void startGame() {
        while (parameters.getState() != GameState.FINISH) {
            T input = inputSource.getNextInput();
            if (!inputValidator.inputIsValid(input, parameters)) {
                parameters.setState(GameState.INVALID_INPUT);
            }
            inputProcessor.processInput(input, parameters);
        }
    }
}
