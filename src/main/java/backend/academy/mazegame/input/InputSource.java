package backend.academy.mazegame.input;

public interface InputSource<InputType> {
    InputType getNextInput();
}
