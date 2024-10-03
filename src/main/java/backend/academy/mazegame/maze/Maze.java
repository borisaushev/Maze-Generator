package backend.academy.mazegame.maze;

public record Maze(char[][] maze) {

    public Maze {
        if (maze.length != maze[0].length) {
            throw new IllegalArgumentException("Maze is not square-shaped");
        }
    }

    public char valueAt(int x, int y) {
        if (x < 0 || x > maze().length || y < 0 || y > maze().length) {
            throw new IllegalArgumentException("Wrong coordinates x: %d \t y: %d".formatted(x, y));
        }
        return maze[x][y];
    }

    public char valueAt(Point point) {
        return valueAt(point.x(), point.y());
    }
}
