package backend.academy.mazegame.representation.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.parameters.MazeSymbols;
import backend.academy.mazegame.representation.MazeRepresentation;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class SimpleMazeRepresentation implements MazeRepresentation<String> {
    @Override
    public String getMazeRepresentation(Maze maze) {
        StringBuilder result = new StringBuilder();
        int maxNumberLength = String.valueOf(maze.maze().length - 1).length();

        result.append(" ".repeat(maxNumberLength)).append("  ");
        for (int x = 0; x < maze.maze().length; x++) {
            int xDigitLength = String.valueOf(x).length();
            result.append(x).append(" ".repeat(maxNumberLength - xDigitLength + 1));
        }
        result.append('\n');

        for (int y = 0; y < maze.maze().length; y++) {
            int yDigitLength = String.valueOf(y).length();
            result.append(y).append(" ".repeat(1 + maxNumberLength - yDigitLength));
            for (int x = 0; x < maze.maze().length; x++) {
                result.append('|').append(String.valueOf(maze.valueAt(x, y)).repeat(maxNumberLength));
            }
            result.append("|\n");
        }

        return result.toString();
    }

    @Override
    public String getMazeRepresentation(Maze maze, List<Point> path) {
        if (path.isEmpty()) {
            return "Пути не нашлось :(";
        }

        StringBuilder result = new StringBuilder();
        int maxNumberLength = String.valueOf(maze.maze().length - 1).length();

        result.append(" ".repeat(maxNumberLength)).append("  ");
        for (int x = 0; x < maze.maze().length; x++) {
            int xDigitLength = String.valueOf(x).length();
            result.append(x).append(" ".repeat(maxNumberLength - xDigitLength + 1));
        }
        result.append('\n');

        for (int y = 0; y < maze.maze().length; y++) {
            int yDigitLength = String.valueOf(y).length();
            result.append(y).append(" ".repeat(1 + maxNumberLength - yDigitLength));
            for (int x = 0; x < maze.maze().length; x++) {
                result.append('|');
                if (path.contains(new Point(x, y))) {
                    result.append(String.valueOf(MazeSymbols.PATH.value()).repeat(maxNumberLength));
                } else {
                    result.append(String.valueOf(maze.valueAt(x, y)).repeat(maxNumberLength));
                }
            }
            result.append("|\n");
        }

        return result.toString();
    }
}
