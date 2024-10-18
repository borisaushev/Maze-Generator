package backend.academy.mazegame.input;

import backend.academy.mazegame.maze.parameters.GameParameters;

public interface InputValidator<T> {
    boolean inputIsValid(T input, GameParameters gameParameters);
}
