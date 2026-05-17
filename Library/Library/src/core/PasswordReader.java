package core;

import java.io.Console;
import java.util.Scanner;

/**
 * Utility class for reading passwords from the terminal.
 *
 * <p>When the JVM is attached to a real terminal, {@link Console#readPassword}
 * is used so that typed characters are not echoed. When running inside an IDE
 * (where {@link System#console()} returns {@code null}), the method falls back
 * to a plain {@link Scanner} read and warns the user that masking is unavailable.
 */
public class PasswordReader {
    public static String readPassword(String prompt) {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword(prompt);
            return new String(passwordArray);
        } else {
            System.out.print(prompt + " Warning: Masking not supported in IDE console ");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }
    }
}