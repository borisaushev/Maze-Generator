package backend.academy.mazegame.maze.parameters;

public enum MazeSymbols {
    WALL('█'),
    SPACE(' '),
    PATH('+'),
    MUD('#'),
    COIN('0');

    public final char value;

    MazeSymbols(char block) {
        value = block;
    }
}
