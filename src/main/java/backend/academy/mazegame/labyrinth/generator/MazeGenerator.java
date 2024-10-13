package backend.academy.mazegame.labyrinth.generator;

import backend.academy.mazegame.maze.Maze;

public interface MazeGenerator {
    Maze generateMaze(int height, int width);
}
