package backend.academy.mazegame.input.impl;

import backend.academy.mazegame.input.InputSource;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Scanner;

@SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class ConsoleInputSource implements InputSource<String> {
    private final Scanner scan = new Scanner(System.in);

    @Override
    public String getNextInput() {
        return scan.nextLine();
    }
}
