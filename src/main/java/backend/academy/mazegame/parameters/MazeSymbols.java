package backend.academy.mazegame.parameters;

import lombok.Getter;

@Getter public enum MazeSymbols {
    WALL('█'),
    SPACE(' '),
    PATH('+');

    final char value;

    MazeSymbols(char block) {
        value = block;
    }

}
