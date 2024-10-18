package backend.academy.mazegame.maze.navigation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.List;
import java.util.Map;

public interface PathFinder {
    List<Point> findPath(Point point1, Point point2, Maze maze);

    List<Point> reconstructPath(Point endingPoint, Map<Point, Point> previousPoint);
}
