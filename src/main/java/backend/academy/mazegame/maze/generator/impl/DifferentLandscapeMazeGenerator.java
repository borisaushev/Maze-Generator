package backend.academy.mazegame.maze.generator.impl;

import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.maze.parameters.MazeSymbols;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import java.util.Random;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.COIN;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.MUD;
import static backend.academy.mazegame.maze.parameters.MazeSymbols.SPACE;

/**
 * uses <b>SimpleMazeGenerator</b> algorithm to generate a maze,
 * then ads COINS and MUD to it
 */
@SuppressFBWarnings("PREDICTABLE_RANDOM")
public class DifferentLandscapeMazeGenerator extends SimpleMazeGenerator {

    private final static Random RANDOM = new Random();

    /**
     * Generates a Maze object based on a given height and width
     *
     * @param height >= Maze.MIN_MAZE_HEIGHT height of a labyrinth
     * @param width  >= Maze.MIN_MAZE_WIDTH width of a labyrinth
     * @return Maze object
     * @throws IllegalArgumentException if <b>height</b> or <b>width</b> is less than
     *                                  Maze.MIN_MAZE_HEIGHT and Maze.MIN_MAZE_WIDTH
     */
    @Override
    public Maze generateMaze(int height, int width) {
        Maze maze = super.generateMaze(height, width);
        addNewBlocks(maze);
        return maze;
    }

    private void addNewBlocks(Maze maze) {
        char[][] mazeMatrix = maze.mazeMatrix();
        for (int y = 0; y < maze.height(); y++) {
            for (int x = 0; x < maze.width(); x++) {
                if (Objects.equals(mazeMatrix[y][x], SPACE.value)) {
                    mazeMatrix[y][x] = getRandomLandscape().value;
                }
            }
        }
    }

    /**
     * Randomly chooses one MazeSymbol of SPACE, MUD and COIN
     *
     * @return One random MazeSymbol: either <b>SPACE(50% chance)</b>
     *     or <b>MUD(25% chance)</b>
     *     or <b>COIN(25% chance)</b>
     */
    private MazeSymbols getRandomLandscape() {
        MazeSymbols[] mazeSymbols = {SPACE, SPACE, MUD, COIN};
        int randomIndex = RANDOM.nextInt(mazeSymbols.length);
        return mazeSymbols[randomIndex];
    }
}
