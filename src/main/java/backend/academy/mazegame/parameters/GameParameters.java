package backend.academy.mazegame.parameters;

import backend.academy.mazegame.labyrinth.generator.MazeGenerator;
import backend.academy.mazegame.labyrinth.navigation.PathFinder;
import backend.academy.mazegame.maze.Maze;

public class GameParameters {
    private GameState state;
    private MazeGenerator generatorAlgorithm;
    private PathFinder pathAlgorithm;
    private Maze maze;

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public PathFinder getPathAlgorithm() {
        return pathAlgorithm;
    }

    public void setPathAlgorithm(PathFinder pathAlgorithm) {
        this.pathAlgorithm = pathAlgorithm;
    }

    public MazeGenerator getGeneratorAlgorithm() {
        return generatorAlgorithm;
    }

    public void setGeneratorAlgorithm(MazeGenerator generatorAlgorithm) {
        this.generatorAlgorithm = generatorAlgorithm;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
