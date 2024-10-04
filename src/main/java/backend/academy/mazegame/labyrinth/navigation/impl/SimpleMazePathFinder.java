package backend.academy.mazegame.labyrinth.navigation.impl;

import backend.academy.mazegame.labyrinth.navigation.PathFinder;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static backend.academy.mazegame.parameters.MazeSymbols.WALL;

/**
 * Finds a way from one <b>Point</b> to another
 */
public class SimpleMazePathFinder implements PathFinder<List<Point>> {
    /**
     * @param startingPoint starting point
     * @param endingPoint   ending point, can equal to starting point
     * @param maze          maze
     * @return <b>Empty List</b> if no path was found, otherwise returns list of all points in any order
     */
    @Override
    public List<Point> findPath(Point startingPoint, Point endingPoint, Maze maze) {
        //change of values of x and y when we go: left, right, up, down
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        LinkedList<Point> que = new LinkedList<>();
        HashSet<Point> visitedPoints = new HashSet<>();
        HashMap<Point, Point> previousPoint = new HashMap<>();
        que.add(startingPoint);
        visitedPoints.add(startingPoint);

        Point currentPoint;
        while (!que.isEmpty()) {
            currentPoint = que.removeFirst();

            if (currentPoint.equals(endingPoint)) {
                return reconstructPath(endingPoint, previousPoint);
            }

            int x = currentPoint.x(), y = currentPoint.y();
            for (int i = 0; i < dx.length; i++) {
                Point newPoint = new Point(x + dx[i], y + dy[i]);
                if (pointIsInBounds(newPoint, maze)
                    && visitedPoints.contains(newPoint) == Boolean.FALSE
                    && maze.valueAt(newPoint) != WALL.value()) {
                    que.addLast(newPoint);
                    previousPoint.put(newPoint, currentPoint);
                    visitedPoints.add(newPoint);
                }
            }
        }

        return List.of();

    }

    /**
     * @param endingPoint   ending point, can equal to starting point
     * @param previousPoint map, where key is some point, and a value is a point that is previous to the key one
     * @return <b>Empty List</b> if no path was found, otherwise returns list of all points in any order
     */
    public List<Point> reconstructPath(Point endingPoint, Map<Point, Point> previousPoint) {
        List<Point> path = new LinkedList<>();

        Point currentPoint = endingPoint;
        while (currentPoint != null) {
            path.add(currentPoint);
            currentPoint = previousPoint.get(currentPoint);
        }

        return path;
    }

    private boolean pointIsInBounds(Point point, Maze maze) {
        int x = point.x(), y = point.y();
        return (x >= 0 && x < maze.maze().length) && (y >= 0 && y < maze.maze().length);
    }

}
