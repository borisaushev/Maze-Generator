package backend.academy.mazegame.input;

import backend.academy.mazegame.maze.Maze;

public interface InputProcessor<InputType> {
    void processInput(InputType input, Maze maze);
}
