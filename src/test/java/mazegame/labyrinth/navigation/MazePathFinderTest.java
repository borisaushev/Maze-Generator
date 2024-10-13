package mazegame.labyrinth.navigation;

import backend.academy.mazegame.labyrinth.navigation.NavigationAlgorithms;
import backend.academy.mazegame.labyrinth.navigation.PathFinder;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazePathFinderTest {
    final static NavigationAlgorithms[] algorithms = NavigationAlgorithms.values();

    @ParameterizedTest
    @FieldSource("algorithms")
    public void getPathTest(NavigationAlgorithms algorithm) {
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
        PathFinder pathFinder = algorithm.value;
        Point end = new Point(2, 2);
        List<Point> expected = List.of(new Point(2, 2), new Point(2, 1), new Point(1, 1), new Point(1, 0));

        List<Point> resultPath = pathFinder.reconstructPath(end, previousPoint);

        assertThat(expected).containsExactlyInAnyOrderElementsOf(resultPath);

    }

    @ParameterizedTest
    @FieldSource("algorithms")
    public void findPathOneWayTest(NavigationAlgorithms algorithm) {
        Maze maze = new Maze(new char[][] {
            {'█', ' ', '█'},
            {'█', ' ', ' '},
            {'█', '█', ' '}
        });

        PathFinder pathFinder = algorithm.value;
        Point start = new Point(1, 0);
        Point end = new Point(2, 2);
        List<Point> expected = List.of(new Point(2, 2), new Point(2, 1), new Point(1, 1), new Point(1, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(expected).containsExactlyInAnyOrderElementsOf(resultPath);

    }

    @ParameterizedTest
    @FieldSource("algorithms")
    public void findPathTwoWaysTest(NavigationAlgorithms algorithm) {
        Maze maze = new Maze(new char[][] {
            {' ', ' ', ' '},
            {' ', '█', ' '},
            {' ', ' ', ' '}
        });

        PathFinder pathFinder = algorithm.value;
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);
        List<Point> expected1 =
            List.of(new Point(2, 2), new Point(1, 2), new Point(0, 2), new Point(0, 1), new Point(0, 0));
        List<Point> expected2 =
            List.of(new Point(2, 2), new Point(2, 1), new Point(2, 0), new Point(1, 0), new Point(0, 0));

        List<Point> resultPath = pathFinder.findPath(start, end, maze);
        System.out.println(algorithm + " " + resultPath);

        boolean isFirstWay = CollectionUtils.isEqualCollection(expected1, resultPath);
        boolean isSecondWay = CollectionUtils.isEqualCollection(expected2, resultPath);

        assertTrue(isFirstWay || isSecondWay);

    }

    @ParameterizedTest
    @FieldSource("algorithms")
    public void findPathNoWaysTest(NavigationAlgorithms algorithm) {
        Maze maze = new Maze(new char[][] {
            {' ', '█', ' '},
            {'█', '█', ' '},
            {' ', ' ', ' '}
        });

        PathFinder pathFinder = algorithm.value;
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        List<Point> resultPath = pathFinder.findPath(start, end, maze);

        assertThat(resultPath).isEmpty();

    }

    @ParameterizedTest
    @FieldSource("algorithms")
    public void findPathToItselfTest(NavigationAlgorithms algorithm) {
        Maze maze = new Maze(new char[][] {
            {' ', '█', ' '},
            {'█', '█', ' '},
            {' ', ' ', ' '}
        });

        PathFinder pathFinder = algorithm.value;
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        List<Point> resultPath = pathFinder.findPath(start, end, maze);
        List<Point> expected = List.of(new Point(0, 0));

        assertThat(resultPath).isEqualTo(expected);
    }
}
