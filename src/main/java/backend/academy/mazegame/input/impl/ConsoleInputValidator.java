package backend.academy.mazegame.input.impl;

import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.labyrinth.generator.GeneratingAlgorithms;
import backend.academy.mazegame.labyrinth.navigation.NavigationAlgorithms;
import backend.academy.mazegame.parameters.GameParameters;
import backend.academy.mazegame.parameters.GameState;
import backend.academy.mazegame.parameters.MazeSymbols;

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
            case GENERATE_MAZE -> integerInputIsValid(input)
                && Integer.parseInt(input) > 2;
            case FIND_PATH -> coordinatesInputIsValid(input, gameParameters);
            case FINISH -> true;
        };
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
        if (!input.matches("\\d+;\\d+ \\d+;\\d+") || parameters.getMaze() == null) {
            return false;
        }

        String[] points = input.split(" ");
        int x1 = Integer.parseInt(points[0].split(";")[0]);
        int y1 = Integer.parseInt(points[0].split(";")[1]);

        int x2 = Integer.parseInt(points[1].split(";")[0]);
        int y2 = Integer.parseInt(points[1].split(";")[1]);

        int length = parameters.getMaze().maze().length;

        return (x1 >= 0 && x1 < length)
            && (x2 >= 0 && x2 < length)
            && (y1 >= 0 && y1 < length)
            && (y2 >= 0 && y2 < length)
            && parameters.getMaze().valueAt(x1, y1) != MazeSymbols.WALL.value()
            && parameters.getMaze().valueAt(x2, y2) != MazeSymbols.WALL.value();
    }

}
