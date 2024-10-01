package backend.academy.mazegame.labyrinth.navigation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;

public interface PathFinder<PathType> {
    PathType findPath(Point point1, Point point2, Maze maze);
}
