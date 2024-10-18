package backend.academy.mazegame.maze.generator;

import backend.academy.mazegame.maze.Maze;

public interface MazeGenerator {
    Maze generateMaze(int height, int width);
}
