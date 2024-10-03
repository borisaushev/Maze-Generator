package backend.academy.mazegame.representation.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.representation.MazeRepresentation;

public class SimpleMazeRepresentation implements MazeRepresentation<String> {
    @Override
    public String getMazeRepresentation(Maze maze) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < maze.maze().length; x++) {
            for (int y = 0; y < maze.maze().length; y++) {
                result.append("|" + maze.valueAt(x, y));
            }
            result.append("|\n");
        }

        return result.toString();
    }
}
