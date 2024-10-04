package backend.academy.mazegame.maze;

public record Point(int x, int y) {

    public boolean equals(Point obj) {
        Point p2 = (Point) obj;
        return p2.x == x && p2.y == y;
    }

    @Override
    public int hashCode() {
        return x * y;
    }
}
