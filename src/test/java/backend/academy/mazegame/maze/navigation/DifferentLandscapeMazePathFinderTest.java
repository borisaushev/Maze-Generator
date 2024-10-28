package backend.academy.mazegame.maze.navigation;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.maze.navigation.impl.DifferentLandscapeMazePathFinder;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferentLandscapeMazePathFinderTest {
    final DifferentLandscapeMazePathFinder pathFinder = new DifferentLandscapeMazePathFinder();

    @Test
    @DisplayName("Only one way")
    public void findPathOneWayTest() {
        Maze maze = new Maze(new char[][] {
            {'█', ' ', '█'},
            {'█', '#', '#'},
            {'█', '█', '0'}
        });
        Point start = new Point(1, 0);
        Point end = new Point(2, 2);
        List<Point> expected = List.of(new Point(2, 2), new Point(2, 1), new Point(1, 1), new Point(1, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(resultPath).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("Two ways with the same cost")
    public void twoWaysEqualCostTest() {
        Maze maze = new Maze(new char[][] {
            {' ', '#', '0'},
            {'#', '█', ' '},
            {' ', '0', ' '}
        });
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);
        List<Point> expected1 =
            List.of(new Point(2, 2), new Point(1, 2), new Point(0, 2), new Point(0, 1), new Point(0, 0));
        List<Point> expected2 =
            List.of(new Point(2, 2), new Point(2, 1), new Point(2, 0), new Point(1, 0), new Point(0, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        boolean isFirstWay = CollectionUtils.isEqualCollection(expected1, resultPath);
        boolean isSecondWay = CollectionUtils.isEqualCollection(expected2, resultPath);

        assertTrue(isFirstWay || isSecondWay);
    }

    @Test
    @DisplayName("Two ways with different cost")
    public void twoWaysDifferentCostTest() {
        Maze maze = new Maze(new char[][] {
            {' ', '#', '0'},
            {'#', '█', '0'},
            {' ', '0', ' '}
        });
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);
        List<Point> expected =
            List.of(new Point(2, 2), new Point(2, 1), new Point(2, 0), new Point(1, 0), new Point(0, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(resultPath).containsExactlyInAnyOrderElementsOf(expected);
    }
}
