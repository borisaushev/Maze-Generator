package backend.academy.mazegame.input;

import backend.academy.mazegame.maze.parameters.GameParameters;

public interface InputProcessor<T> {
    void processInput(T input, GameParameters parameters);

    void greetUser();
}
