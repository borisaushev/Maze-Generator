package backend.academy.mazegame;

import backend.academy.mazegame.input.InputProcessor;
import backend.academy.mazegame.input.InputSource;
import backend.academy.mazegame.input.InputValidator;
import backend.academy.mazegame.input.impl.ConsoleInputProcessor;
import backend.academy.mazegame.input.impl.ConsoleInputSource;
import backend.academy.mazegame.input.impl.ConsoleInputValidator;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleMazeGameStarter {
    public void startConsoleMazeGame() {
        InputSource<String> inputSource = new ConsoleInputSource();
        InputValidator<String> inputValidator = new ConsoleInputValidator();
        InputProcessor<String> inputProcessor = new ConsoleInputProcessor();
        MazeGameRunner<String> mazeGameRunner = new MazeGameRunner<>(inputSource, inputValidator, inputProcessor);

        System.out.println("""
            1. Изменить алгоритм генерации лабиринта
            2. Изменить алгоритм поиска пути
            3. Сгенерировать лабиринт
            4. Найти путь в лабиринте
            5. Закончить игру
            """);
        mazeGameRunner.startGame();
    }
}
