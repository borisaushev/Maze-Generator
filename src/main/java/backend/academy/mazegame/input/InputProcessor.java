package backend.academy.mazegame.input;

import backend.academy.mazegame.parameters.GameParameters;

public interface InputProcessor<T> {
    void processInput(T input, GameParameters parameters);
}
