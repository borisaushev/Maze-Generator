package backend.academy.mazegame.parameters;

public enum MazeSymbols {
    WALL('█'),
    SPACE(' '),
    PATH('+');

    public final char value;

    MazeSymbols(char block) {
        value = block;
    }
}
