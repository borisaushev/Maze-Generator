package backend.academy.mazegame.maze.generator.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.maze.generator.MazeGenerator;
import backend.academy.mazegame.maze.parameters.MazeSymbols;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Generates a maze using Prims algorithm
 *
 * @see <a href="https://habr.com/ru/articles/537630/">
 *     see this article for more information</a>
 */
@SuppressFBWarnings({"PREDICTABLE_RANDOM", "PL_PARALLEL_LISTS", "CLI_CONSTANT_LIST_INDEX"})
public class PrimsMazeGenerator implements MazeGenerator {
    private static final int LEFT = -2;
    private static final int RIGHT = 2;
    private static final int DOWN = -2;
    private static final int UP = 2;
    private static final int NO_CHANGE = 0;
    private final Random random = new Random();
    //change of values of x and y when we go: left, right, up, down
    private final int[] dx = {LEFT, RIGHT, NO_CHANGE, NO_CHANGE};
    private final int[] dy = {NO_CHANGE, NO_CHANGE, UP, DOWN};
    private char[][] mazeMatrix;

    /**
     * Generates a Maze object based on a given height and width
     *
     * @param height >= MIN_MAZE_HEIGHT height of a labyrinth
     * @param width  >= MIN_MAZE_WIDTH width of a labyrinth
     * @return Maze object
     * @throws IllegalArgumentException if <b>height or width</b> is less than MIN_MAZE_HEIGHT and MIN_MAZE_WIDTH
     */
    @Override
    public Maze generateMaze(int height, int width) {
        if (!validateMazeParameters(height, width)) {
            throw new IllegalArgumentException("height and width are supposed to be at least "
                + Maze.MIN_MAZE_HEIGHT + " : " + Maze.MIN_MAZE_WIDTH);
        }
        // Create an array and add valid points that are two orthogonal spaces away from the point you just cleared.
        ArrayList<Point> availablePointsList = new ArrayList<>();
        HashSet<Point> visitedPoints = new HashSet<>();
        mazeMatrix = new char[height][width];
        clearMaze();
        // Choose a random point with odd x and y coordinates and clear it.
        int x = random.nextInt(0, width / 2) * 2 + 1;
        int y = random.nextInt(0, height / 2) * 2 + 1;
        mazeMatrix[y][x] = MazeSymbols.SPACE.value;
        visitedPoints.add(new Point(x, y));
        addNearestPoints(x, y, visitedPoints, availablePointsList);

        // While there are points in your growable array, choose one at random,
        // clear it, and remove it from the growable array.
        while (!availablePointsList.isEmpty()) {
            int index = random.nextInt(0, availablePointsList.size());
            Point point = availablePointsList.remove(index);
            x = point.x();
            y = point.y();
            mazeMatrix[y][x] = MazeSymbols.SPACE.value;
            visitedPoints.add(point);
            // The point you just cleared needs to be connected with another clear point.
            connectNewPoint(x, y);
            //Add all available point
            addNearestPoints(x, y, visitedPoints, availablePointsList);
        }

        return new Maze(mazeMatrix);
    }

    private void connectNewPoint(int x, int y) {
        // Look two orthogonal spaces away from the point you just cleared until you find one that is not a wall.
        // Clear the point between them.
        List<Integer> randomDeltaIndexes = new ArrayList<>(dx.length);
        for (int i = 0; i < dx.length; i++) {
            randomDeltaIndexes.add(i);
        }
        while (!randomDeltaIndexes.isEmpty()) {
            int randomDeltaIndex = random.nextInt(0, randomDeltaIndexes.size());
            int dirIndex = randomDeltaIndexes.get(randomDeltaIndex);
            int newX = x + dx[dirIndex];
            int newY = y + dy[dirIndex];
            if (pointIsInBounds(newX, newY) && mazeMatrix[newY][newX] == MazeSymbols.SPACE.value) {
                mazeMatrix[y + (dy[dirIndex] / 2)][x + (dx[dirIndex] / 2)] = MazeSymbols.SPACE.value;
                break;
            }
            randomDeltaIndexes.remove(randomDeltaIndex);
        }
    }

    private void addNearestPoints(
        int x, int y,
        HashSet<Point> visitedPoints,
        ArrayList<Point> availablePointsList
    ) {
        // Add valid points that are two orthogonal spaces away from the point you cleared.
        for (int i = 0; i < dx.length; i++) {
            int deltaX = dx[i];
            int deltaY = dy[i];
            Point newPoint = new Point(x + deltaX, y + deltaY);
            if (pointIsInBounds(newPoint)
                && mazeMatrix[y + deltaY][x + deltaX] == MazeSymbols.WALL.value
                && !visitedPoints.contains(newPoint)) {
                availablePointsList.add(newPoint);
                visitedPoints.add(newPoint);
            }
        }
    }

    private void clearMaze() {
        for (int x = 0; x < mazeMatrix[0].length; x++) {
            for (int y = 0; y < mazeMatrix.length; y++) {
                mazeMatrix[y][x] = MazeSymbols.WALL.value;
            }
        }
    }

    private boolean validateMazeParameters(int height, int width) {
        return height >= Maze.MIN_MAZE_HEIGHT
            && width >= Maze.MIN_MAZE_WIDTH;
    }

    private boolean pointIsInBounds(int x, int y) {
        return (x >= 0 && x < mazeMatrix[0].length) && (y >= 0 && y < mazeMatrix.length);
    }

    private boolean pointIsInBounds(Point point) {
        return pointIsInBounds(point.x(), point.y());
    }
}
