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
import java.util.Objects;
import java.util.PriorityQueue;
import org.apache.commons.math3.util.Pair;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.COIN;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.MUD;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.SPACE;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.WALL;

/**
 * Finds a way from one <b>Point</b> to another,
 * works with simple mazes, and mazes containing <b>COIN</b> and <b>MUD</b> symbols
 */
@SuppressFBWarnings("PL_PARALLEL_LISTS")
public class DifferentLandscapeMazePathFinder implements PathFinder {
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int DOWN = -1;
    private static final int UP = 1;
    private static final int NO_CHANGE = 0;
    //costs of different landscapes
    private static final int COIN_COST = 0;
    private static final int SPACE_COST = 1;
    private static final int MUD_COST = 2;
    //change of values of x and y when we go: left, right, up, down
    private final int[] dx = {LEFT, RIGHT, NO_CHANGE, NO_CHANGE};
    private final int[] dy = {NO_CHANGE, NO_CHANGE, UP, DOWN};
    HashSet<Point> visitedPoints;
    PriorityQueue<PointAndItsCost> que;
    HashMap<Point, Point> previousPoint;

    /**
     * Finds and returns a path between 2 given points in a given mazeMatrix using Dijkstra algorithm
     *
     * @param startingPoint starting point
     * @param endingPoint   ending point, can equal to starting point
     * @param maze          maze
     * @return <b>Empty List</b> if no path was found, otherwise returns list of all points in any order
     */
    @Override
    public List<Point> findPath(Point startingPoint, Point endingPoint, Maze maze) {
        previousPoint = new HashMap<>();
        visitedPoints = new HashSet<>();
        que = new PriorityQueue<>();
        que.add(new PointAndItsCost(0, startingPoint));

        while (!que.isEmpty()) {
            PointAndItsCost pair = que.poll();
            Point currentPoint = pair.getSecond();
            if (currentPoint.equals(endingPoint)) {
                return reconstructPath(endingPoint, previousPoint);
            }

            int cost = pair.getFirst();
            int x = currentPoint.x();
            int y = currentPoint.y();
            visitedPoints.add(currentPoint);

            for (int i = 0; i < dx.length; i++) {
                Point newPoint = new Point(x + dx[i], y + dy[i]);
                if (maze.pointIsInBounds(newPoint)
                    && !visitedPoints.contains(newPoint)
                    && maze.valueAt(newPoint) != WALL.value) {
                    previousPoint.put(newPoint, currentPoint);
                    int newCost = cost + getSymbolCost(maze.valueAt(currentPoint));
                    que.add(new PointAndItsCost(newCost, newPoint));
                }
            }
        }
        return List.of();
    }

    private int getSymbolCost(char symbol) {
        if (symbol == COIN.value) {
            return COIN_COST;
        } else if (symbol == SPACE.value) {
            return SPACE_COST;
        } else if (symbol == MUD.value) {
            return MUD_COST;
        }
        throw new IllegalArgumentException("Illegal symbol value, only allowed are: COIN, SPACE, MUD");
    }

    @Override
    public List<Point> reconstructPath(Point endingPoint, Map<Point, Point> previousPoint) {
        List<Point> path = new LinkedList<>();

        Point currentPoint = endingPoint;
        while (currentPoint != null) {
            path.add(currentPoint);
            currentPoint = previousPoint.get(currentPoint);
        }
        return path;
    }

    /**
     * Utility class used only to avoid writing complicated code
     * to implement Dijkstra algorithm
     */
    static class PointAndItsCost extends Pair<Integer, Point> implements Comparable<PointAndItsCost> {
        PointAndItsCost(Integer integer, Point point) {
            super(integer, point);
        }

        @Override
        public int compareTo(PointAndItsCost otherPoint) {
            if (getFirst() < otherPoint.getFirst()) {
                return -1;
            } else if (Objects.equals(getFirst(), otherPoint.getFirst())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
