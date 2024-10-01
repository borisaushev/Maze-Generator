package backend.academy.mazegame;

import backend.academy.mazegame.labyrinth.generator.MazeGenerator;
import backend.academy.mazegame.maze.Maze;

public class MazeGameStarter {
    Maze maze;
    MazeGenerator mazeGenerator;

    void startGame() {
        maze = mazeGenerator.generateMaze();
    }
}
