package backend.academy.mazegame.maze;

public record Point (int x, int y) {
    public Point {
        if (x < 1 || y < 1)
            throw new IllegalArgumentException("Wrong coordinates: " + x + " " + y);
    }
}
