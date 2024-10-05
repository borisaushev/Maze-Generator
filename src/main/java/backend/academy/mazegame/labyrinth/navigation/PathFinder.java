package backend.academy.mazegame.labyrinth.navigation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.List;

public interface PathFinder {
    List<Point> findPath(Point point1, Point point2, Maze maze);
}
