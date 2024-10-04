package mazegame.labyrinth.navigation;

import backend.academy.mazegame.labyrinth.navigation.impl.SimpleMazePathFinder;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleMazePathFinderTest {
    @Test
    public void getPathTest() {
        /*
        |#| |#|
        |#| | |
        |#|#| |
         */
        Map<Point, Point> previousPoint = Map.of(
            new Point(2, 2), new Point(2, 1),
            new Point(2, 1), new Point(1, 1),
            new Point(1, 1), new Point(1, 0)
        );
        SimpleMazePathFinder pathFinder = new SimpleMazePathFinder();
        Point end = new Point(2, 2);
        List<Point> expected = List.of(new Point(2, 2), new Point(2, 1), new Point(1, 1), new Point(1, 0));

        List<Point> resultPath = pathFinder.reconstructPath(end, previousPoint);

        assertThat(expected).containsExactlyInAnyOrderElementsOf(resultPath);

    }

    @Test
    public void findPathOneWayTest() {
        Maze maze = new Maze(new char[][] {
            {'█', ' ', '█'},
            {'█', ' ', ' '},
            {'█', '█', ' '}
        });

        SimpleMazePathFinder pathFinder = new SimpleMazePathFinder();
        Point start = new Point(1, 0);
        Point end = new Point(2, 2);
        List<Point> expected = List.of(new Point(2, 2), new Point(2, 1), new Point(1, 1), new Point(1, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(expected).containsExactlyInAnyOrderElementsOf(resultPath);

    }

    @Test
    public void findPathTwoWaysTest() {
        Maze maze = new Maze(new char[][] {
            {' ', ' ', ' '},
            {' ', '█', ' '},
            {' ', ' ', ' '}
        });

        SimpleMazePathFinder pathFinder = new SimpleMazePathFinder();
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
    public void findPathNoWaysTest() {
        Maze maze = new Maze(new char[][] {
            {' ', '█', ' '},
            {'█', '█', ' '},
            {' ', ' ', ' '}
        });

        SimpleMazePathFinder pathFinder = new SimpleMazePathFinder();
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(resultPath).isEmpty();

    }

    @Test
    public void findPathToItselfTest() {
        Maze maze = new Maze(new char[][] {
            {' ', '█', ' '},
            {'█', '█', ' '},
            {' ', ' ', ' '}
        });

        SimpleMazePathFinder pathFinder = new SimpleMazePathFinder();
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        List<Point> resultPath = pathFinder.findPath(start, end, maze);
        List<Point> expected = List.of(new Point(0, 0));

        assertThat(resultPath).isEqualTo(expected);

    }

}
