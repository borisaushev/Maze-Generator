package backend.academy.mazegame.input.impl;

import backend.academy.mazegame.input.InputProcessor;
import backend.academy.mazegame.labyrinth.generator.GeneratingAlgorithms;
import backend.academy.mazegame.labyrinth.navigation.NavigationAlgorithms;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.Point;
import backend.academy.mazegame.parameters.GameParameters;
import backend.academy.mazegame.parameters.GameState;
import backend.academy.mazegame.representation.MazeRepresentation;
import backend.academy.mazegame.representation.impl.SimpleMazeRepresentation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import static backend.academy.mazegame.labyrinth.generator.GeneratingAlgorithms.getAllGeneratingAlgorithmsWithDescriptions;
import static backend.academy.mazegame.labyrinth.navigation.NavigationAlgorithms.getAllNavigationAlgorithmsWithDescriptions;
import static backend.academy.mazegame.parameters.GameState.CHOOSE_MAIN_MENU_OPTION;
import static backend.academy.mazegame.parameters.GameState.FINISH;

@SuppressWarnings({"RegexpSinglelineJava", "MissingSwitchDefault"})
@SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
public class ConsoleInputProcessor implements InputProcessor<String> {
    private final static String MENU_OPTIONS = """
        1. Изменить алгоритм генерации лабиринта
        2. Изменить алгоритм поиска пути
        3. Сгенерировать лабиринт
        4. Найти путь в лабиринте
        5. Закончить игру
        """;

    /**
     * processes String input based on game parameters
     *
     * @param input      String input
     * @param parameters set of parameters that define our actions with input
     */
    @Override
    public void processInput(String input, GameParameters parameters) {
        GameState gameState = parameters.getState();
        switch (gameState) {
            case CHOOSE_MAIN_MENU_OPTION -> {
                GameState newGameState = GameState.getInputType(getIntegerInput(input));
                parameters.setState(newGameState);
                String additionalInfo = getAdditionalInputInfo(newGameState);
                System.out.println(additionalInfo);
            }
            case INVALID_INPUT -> {
                System.out.println("Что то вы напутали со вводом\n");
                parameters.setState(CHOOSE_MAIN_MENU_OPTION);
                System.out.println(MENU_OPTIONS);
            }
            case CHANGE_PATH_ALGORITHM -> {
                int type = getIntegerInput(input);
                parameters.setPathAlgorithm(NavigationAlgorithms.getAlgorithm(type));
                parameters.setState(CHOOSE_MAIN_MENU_OPTION);
                System.out.println(MENU_OPTIONS);
            }
            case CHANGE_GENERATING_ALGORITHM -> {
                int type = getIntegerInput(input);
                parameters.setGeneratorAlgorithm(GeneratingAlgorithms.getAlgorithm(type));
                parameters.setState(CHOOSE_MAIN_MENU_OPTION);
                System.out.println(MENU_OPTIONS);
            }
            case GENERATE_MAZE -> {
                Maze maze = generateMaze(input, parameters);
                printMaze(maze);
                parameters.setMaze(maze);
                parameters.setState(CHOOSE_MAIN_MENU_OPTION);
                System.out.println(MENU_OPTIONS);
            }
            case FIND_PATH -> {
                findPath(input, parameters);
                parameters.setState(CHOOSE_MAIN_MENU_OPTION);
                System.out.println(MENU_OPTIONS);
            }
            case FINISH -> parameters.setState(FINISH);
        }
    }

    private String getAdditionalInputInfo(GameState newGameState) {
        return switch (newGameState) {
            case CHOOSE_MAIN_MENU_OPTION ->
                throw new IllegalStateException("Error in program code, illegal type CHOOSE_MAIN_MENU_OPTION");
            case INVALID_INPUT -> throw new IllegalStateException("Error in program code, illegal type INVALID_INPUT");
            case CHANGE_GENERATING_ALGORITHM -> getAllGeneratingAlgorithmsWithDescriptions();
            case CHANGE_PATH_ALGORITHM -> getAllNavigationAlgorithmsWithDescriptions();
            case GENERATE_MAZE -> ("Введите высоту и ширину лабиринта(2 числа через пробел, "
                + "высота не меньше %d ширина не меньше %d)").formatted(Maze.MIN_MAZE_HEIGHT, Maze.MIN_MAZE_WIDTH);
            case FIND_PATH -> "Введите координаты начальной и конечной точки в виде x1;y1 x2;y2";
            case FINISH -> "Спасибо за игру!";
        };
    }

    private Maze generateMaze(String input, GameParameters parameters) {
        int height = getIntegerInput(input.split(" ")[0]);
        int width = getIntegerInput(input.split(" ")[1]);
        return parameters.getGeneratorAlgorithm().generateMaze(height, width);
    }

    private void printMaze(Maze maze) {
        MazeRepresentation<String> representation = new SimpleMazeRepresentation();
        System.out.println(representation.getMazeRepresentation(maze));
    }

    private void findPath(String input, GameParameters parameters) {
        Maze maze = parameters.getMaze();
        Point[] points = parsePoints(input);
        Point start = points[0];
        Point end = points[1];

        List<Point> path = parameters.getPathAlgorithm().findPath(start, end, maze);
        MazeRepresentation<String> representation = new SimpleMazeRepresentation();
        System.out.println(representation.getMazeRepresentation(maze, path));
    }

    public int getIntegerInput(String input) {
        return Integer.parseInt(input);
    }

    /**
     * parses an already valid String representing 2 points
     *
     * @param input String matching \\d;\\d \\d;\\d expression, representing x1;y1 x2;y2
     * @return An array of 2 Points with coordinates (x1,y1) and (x2,y2)
     */
    public Point[] parsePoints(String input) {
        //x1;y1 x2;y2
        Point[] res = new Point[2];
        String[] points = input.split(" ");
        int x1 = Integer.parseInt(points[0].split(";")[0]);
        int y1 = Integer.parseInt(points[0].split(";")[1]);

        int x2 = Integer.parseInt(points[1].split(";")[0]);
        int y2 = Integer.parseInt(points[1].split(";")[1]);

        res[0] = new Point(x1, y1);
        res[1] = new Point(x2, y2);

        return res;
    }
}
