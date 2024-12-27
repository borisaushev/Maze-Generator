package backend.academy.mazegame.parameters;

import lombok.Getter;

@Getter public enum MazeSymbols {
    WALL('█'),
    SPACE(' ');

    final char value;

    MazeSymbols(char block) {
        value = block;
    }

}
