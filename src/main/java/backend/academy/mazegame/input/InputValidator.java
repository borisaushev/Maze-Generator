package backend.academy.mazegame.input;

public interface InputValidator<InputType> {
    boolean inputIsValid(InputType input);
}
