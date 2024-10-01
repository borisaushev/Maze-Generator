package backend.academy.mazegame.representation;

import backend.academy.mazegame.maze.Maze;

public interface MazeRepresentation<RepresentationType> {
    RepresentationType getMazeRepresentation(Maze maze);
}
