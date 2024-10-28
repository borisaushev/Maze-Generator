package backend.academy.mazegame.representation.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.maze.parameters.MazeSymbols;
import backend.academy.mazegame.representation.MazeRepresentation;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class SimpleMazeRepresentation implements MazeRepresentation<String> {
    @Override
    public String getMazeRepresentation(Maze maze) {
        StringBuilder result = new StringBuilder();
        int maxNumberLength = String.valueOf(
            Math.max(maze.height() - 1, maze.width() - 1)).length();

        addTopCoordinates(result, maze, maxNumberLength);
        addMazeBody(result, maze, maxNumberLength);
        addBottomBorder(result, maze, maxNumberLength);

        return result.toString();
    }

    private void addTopCoordinates(StringBuilder result, Maze maze, int maxNumberLength) {
        result.append(" ".repeat(maxNumberLength)).append("  ");
        for (int x = 0; x < maze.width(); x++) {
            int xDigitLength = String.valueOf(x).length();
            result.append(x).append(" ".repeat(maxNumberLength - xDigitLength + 1));
        }
        result.append('\n');
    }

    private void addMazeBody(StringBuilder result, Maze maze, int maxNumberLength) {
        for (int y = 0; y < maze.height(); y++) {
            int yDigitLength = String.valueOf(y).length();
            result.append(y).append(" ".repeat(1 + maxNumberLength - yDigitLength));
            for (int x = 0; x < maze.width(); x++) {
                result.append('|').append(String.valueOf(maze.valueAt(x, y)).repeat(maxNumberLength));
            }
            //add right border if needed
            if (maze.width() % 2 == 0) {
                result.append('|');
                result.append(String.valueOf(MazeSymbols.WALL.value).repeat(maxNumberLength));
            }
            result.append("|\n");
        }
    }

    private void addBottomBorder(StringBuilder result, Maze maze, int maxNumberLength) {
        //add bottom border if needed
        if (maze.height() % 2 == 0) {
            result.append(" ".repeat(maxNumberLength)).append(' ');
            for (int i = 0; i < maze.width() + ((maze.width() + 1) % 2); i++) {
                result.append('|');
                result.append(String.valueOf(MazeSymbols.WALL.value).repeat(maxNumberLength));
            }
            result.append("|\n");
        }
    }

    @Override
    public String getMazeRepresentation(Maze maze, List<Point> path) {
        if (path.isEmpty()) {
            return "Пути не нашлось :(";
        }

        char[][] mazeCopy = new char[maze.height()][maze.width()];
        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                if (path.contains(new Point(x, y))) {
                    mazeCopy[y][x] = MazeSymbols.PATH.value;
                } else {
                    mazeCopy[y][x] = maze.valueAt(x, y);
                }
            }
        }

        return getMazeRepresentation(new Maze(mazeCopy));
    }
}
