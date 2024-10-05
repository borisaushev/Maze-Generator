package backend.academy.mazegame.representation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.List;

public interface MazeRepresentation<T> {
    T getMazeRepresentation(Maze maze);

    T getMazeRepresentation(Maze maze, List<Point> path);
}
