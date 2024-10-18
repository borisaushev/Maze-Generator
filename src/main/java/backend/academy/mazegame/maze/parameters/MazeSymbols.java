package backend.academy.mazegame.maze.parameters;

public enum MazeSymbols {
    WALL('█'),
    SPACE(' '),
    PATH('+');

    public final char value;

    MazeSymbols(char block) {
        value = block;
    }
}
