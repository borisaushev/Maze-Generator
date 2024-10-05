package backend.academy.mazegame.maze;

public record Point(int x, int y) {

    public boolean equals(Object obj) {
        if (obj instanceof Point point) {
            return point.x == x && point.y == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * y;
    }
}
