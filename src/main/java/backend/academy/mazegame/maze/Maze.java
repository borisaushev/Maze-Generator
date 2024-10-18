package backend.academy.mazegame.maze;

public record Maze(char[][] mazeMatrix) {
    public static final int MIN_MAZE_HEIGHT = 3;
    public static final int MIN_MAZE_WIDTH = 3;

    public Maze {
        if (mazeMatrix.length < MIN_MAZE_HEIGHT || mazeMatrix[0].length < MIN_MAZE_WIDTH) {
            throw new IllegalArgumentException("height and width are supposed to be at least "
                + Maze.MIN_MAZE_HEIGHT + " : " + Maze.MIN_MAZE_WIDTH);
        }
    }

    public int height() {
        return mazeMatrix.length;
    }

    public int width() {
        return mazeMatrix[0].length;
    }

    public char valueAt(int x, int y) {
        if (x < 0 || x > mazeMatrix()[0].length || y < 0 || y > mazeMatrix().length) {
            throw new IllegalArgumentException("Wrong coordinates x: %d \t y: %d".formatted(x, y));
        }
        return mazeMatrix[y][x];
    }

    public char valueAt(Point point) {
        return valueAt(point.x(), point.y());
    }

    public boolean pointIsInBounds(Point point) {
        int x = point.x();
        int y = point.y();
        return (x >= 0 && x < width()) && (y >= 0 && y < height());
    }
}
