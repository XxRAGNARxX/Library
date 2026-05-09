package core;

import java.io.Console;
import java.util.Scanner;

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