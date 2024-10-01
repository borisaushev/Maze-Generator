package backend.academy.mazegame.maze;

public record Maze(char[][] maze) {
    public Maze(int length) {
        this(new char[length + 1][length + 1]);
    }

    public char valueAt(int x, int y) {
        return maze[x][y];
    }
    public char valueAt(Point point) {
        return valueAt(point.x(), point.y());
    }
}
