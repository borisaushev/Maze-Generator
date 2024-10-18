package backend.academy.mazegame.maze.navigation.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.maze.navigation.PathFinder;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.WALL;

/**
 * Finds a way from one <b>Point</b> to another
 */
@SuppressFBWarnings("PL_PARALLEL_LISTS")
public class BFSMazePathFinder implements PathFinder {
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int DOWN = -1;
    private static final int UP = 1;
    private static final int NO_CHANGE = 0;
    //change of values of x and y when we go: left, right, up, down
    private final int[] dx = {LEFT, RIGHT, NO_CHANGE, NO_CHANGE};
    private final int[] dy = {NO_CHANGE, NO_CHANGE, UP, DOWN};

    /**
     * Finds and returns a path between 2 given points in a given mazeMatrix using BFS algorithm
     *
     * @param startingPoint starting point
     * @param endingPoint   ending point, can equal to starting point
     * @param maze          mazeMatrix
     * @return <b>Empty List</b> if no path was found, otherwise returns list of all points in any order
     */
    @Override
    public List<Point> findPath(Point startingPoint, Point endingPoint, Maze maze) {
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

            int x = currentPoint.x();
            int y = currentPoint.y();
            for (int i = 0; i < dx.length; i++) {
                Point newPoint = new Point(x + dx[i], y + dy[i]);
                if (maze.pointIsInBounds(newPoint)
                    && !visitedPoints.contains(newPoint)
                    && maze.valueAt(newPoint) != WALL.value) {
                    que.addLast(newPoint);
                    previousPoint.put(newPoint, currentPoint);
                    visitedPoints.add(newPoint);
                }
            }
        }
        return List.of();
    }

    /**
     * Reconstructs and returns a path of Point object,
     * based on an Ending point and a map of previous points to given ones
     *
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
}
