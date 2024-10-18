package backend.academy.mazegame.input.impl;

import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.generator.GeneratingAlgorithms;
import backend.academy.mazegame.maze.navigation.NavigationAlgorithms;
import backend.academy.mazegame.maze.parameters.GameParameters;
import backend.academy.mazegame.maze.parameters.GameState;
import backend.academy.mazegame.maze.parameters.MazeSymbols;

public class ConsoleInputValidator implements InputValidator<String> {
    @Override
    public boolean inputIsValid(String input, GameParameters gameParameters) {
        GameState gameState = gameParameters.getState();
        return switch (gameState) {
            case CHOOSE_MAIN_MENU_OPTION -> integerInputIsValid(input)
                && Integer.parseInt(input) <= GameState.values().length;
            case INVALID_INPUT -> false;
            case CHANGE_GENERATING_ALGORITHM -> integerInputIsValid(input)
                && Integer.parseInt(input) <= GeneratingAlgorithms.values().length;
            case CHANGE_PATH_ALGORITHM -> integerInputIsValid(input)
                && Integer.parseInt(input) <= NavigationAlgorithms.values().length;
            case GENERATE_MAZE -> mazeParametersAreValid(input);
            case FIND_PATH -> gameParameters.getMaze() != null
                && coordinatesInputIsValid(input, gameParameters);
            case FINISH -> true;
        };
    }

    public boolean mazeParametersAreValid(String input) {
        //height width
        return input.matches("\\d+ \\d+")
            && Integer.parseInt(input.split(" ")[0]) >= Maze.MIN_MAZE_HEIGHT
            && Integer.parseInt(input.split(" ")[1]) >= Maze.MIN_MAZE_WIDTH;
    }

    public boolean integerInputIsValid(String input) {
        try {
            return Integer.parseInt(input) > 0;
        } catch (NumberFormatException exc) {
            return false;
        }
    }

    public boolean coordinatesInputIsValid(String input, GameParameters parameters) {
        //x1;y1 x2;y2
        if (!input.matches("\\d+;\\d+ \\d+;\\d+")) {
            return false;
        }

        String[] points = input.split(" ");
        int x1 = Integer.parseInt(points[0].split(";")[0]);
        int y1 = Integer.parseInt(points[0].split(";")[1]);

        int x2 = Integer.parseInt(points[1].split(";")[0]);
        int y2 = Integer.parseInt(points[1].split(";")[1]);

        int height = parameters.getMaze().height();
        int width = parameters.getMaze().width();

        return (x1 >= 0 && x1 < width)
            && (x2 >= 0 && x2 < width)
            && (y1 >= 0 && y1 < height)
            && (y2 >= 0 && y2 < height)
            && parameters.getMaze().valueAt(x1, y1) != MazeSymbols.WALL.value
            && parameters.getMaze().valueAt(x2, y2) != MazeSymbols.WALL.value;
    }
}
