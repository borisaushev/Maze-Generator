package backend.academy.mazegame.labyrinth.generator.impl;

import backend.academy.mazegame.labyrinth.generator.MazeGenerator;
import backend.academy.mazegame.maze.Maze;
import backend.academy.mazegame.parameters.MazeSymbols;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;

/**
 * Generates a simple labyrinth, with only walls and spaces
 *
 * @see <a href="https://www.google.com/amp/s/habr.com/ru/amp/publications/318530/">source article</a>
 */
@SuppressFBWarnings({"PREDICTABLE_RANDOM", "PL_PARALLEL_LISTS", "CLI_CONSTANT_LIST_INDEX"})
public class SimpleMazeGenerator implements MazeGenerator {
    private final Random random = new Random();
    //change of values of x and y when we go: left, right, up, down
    private final int[] dx = {-2, 2, 0, 0};
    private final int[] dy = {0, 0, 2, -2};
    private int currentX;
    private int currentY;
    private char[][] maze;

    /**
     * Generates a Maze object based on a given height and width
     *
     * @param height > MIN_MAZE_HEIGHT height of a labyrinth
     * @param width  > MIN_MAZE_WIDTH width of a labyrinth
     * @return Maze object
     * @throws IllegalArgumentException if <b>height or width</b> is less than MIN_MAZE_HEIGHT and MIN_MAZE_WIDTH
     */
    @Override
    public Maze generateMaze(int height, int width) {
        if (!validateMazeParameters(height, width)) {
            throw new IllegalArgumentException("height and width are supposed to be at least "
                + Maze.MIN_MAZE_HEIGHT + " : " + Maze.MIN_MAZE_WIDTH);
        }
        maze = new char[height][width];
        clearMaze();
        int deletedWalls = 0;
        setRandomCoordinates();

        //number of points with both of its coordinates being even
        //F.E. (1,1), (1,3), (3,3)
        int wallsToDelete = (height / 2) * (width / 2);
        while (deletedWalls < wallsToDelete) {
            maze[currentY][currentX] = MazeSymbols.SPACE.value;
            deletedWalls += 1;

            boolean cantJumpNowhere = true;
            //checking if we can't step nowhere
            for (int i = 0; i < dx.length; i++) {
                if (pointIsInBounds(currentX + dx[i], currentY + dy[i])) {
                    if (maze[currentY + dy[i]][currentX + dx[i]] == MazeSymbols.WALL.value) {
                        cantJumpNowhere = false;
                        break;
                    }
                }
            }
            if (cantJumpNowhere) {
                if (deletedWalls < wallsToDelete) {
                    setRandomCoordinates();
                }
                continue;
            }
            makeNewRandomStep();
        }
        return new Maze(maze);
    }

    private void clearMaze() {
        for (int x = 0; x < maze[0].length; x++) {
            for (int y = 0; y < maze.length; y++) {
                maze[y][x] = MazeSymbols.WALL.value;
            }
        }
    }

    private void makeNewRandomStep() {
        //now we know we have somewhere to go, and we just pick it randomly
        int dir;
        int newX;
        int newY;
        do {
            dir = random.nextInt(0, dx.length);
            newX = currentX + dx[dir];
            newY = currentY + dy[dir];
        } while (!pointIsInBounds(newX, newY) || maze[newY][newX] == MazeSymbols.SPACE.value);

        //making a way to (newX, newY)
        maze[newY][newX] = MazeSymbols.SPACE.value;
        //deleting a wall at the point we just 'jumped over'
        maze[newY - dy[dir] / 2][newX - dx[dir] / 2] = MazeSymbols.SPACE.value;

        currentX = newX;
        currentY = newY;
    }

    private void setRandomCoordinates() {
        do {
            currentX = random.nextInt(0, maze[0].length / 2) * 2 + 1;
            currentY = random.nextInt(0, maze.length / 2) * 2 + 1;
        } while (maze[currentY][currentX] != MazeSymbols.WALL.value);
    }

    public boolean validateMazeParameters(int height, int width) {
        return height >= Maze.MIN_MAZE_HEIGHT
            && width >= Maze.MIN_MAZE_WIDTH;
    }

    private boolean pointIsInBounds(int x, int y) {
        return (x >= 0 && x < maze[0].length) && (y >= 0 && y < maze.length);
    }
}

