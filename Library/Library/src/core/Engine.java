package core;

import commands.Command;
import commands.CommandsIndex;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;

import java.util.Scanner;

public class Engine {
    private boolean isRunning;
    private final LibraryData libraryData;
    private final FileActions fileActions;
    private final CommandFactory commandFactory;

    public Engine(LibraryData libraryData, FileActions fileActions) {
        this.isRunning = true;
        this.libraryData = libraryData;
        this.fileActions = fileActions;
        this.commandFactory = new CommandFactory(libraryData, fileActions);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library System");

        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            processInput(input);
        }
        scanner.close();
    }

    private void processInput(String input) {
        String[] tokens = input.split("\\s+");
        String commandKey = tokens[0];

        if (tokens.length > 1 && (tokens[0].equalsIgnoreCase("books")
                || tokens[0].equalsIgnoreCase("users")
                || tokens[0].equalsIgnoreCase("save"))) {
            commandKey = tokens[0] + " " + tokens[1];
        }

        CommandsIndex index = CommandsIndex.fromString(commandKey);

        if (index == CommandsIndex.EXIT) {
            System.out.println("Exiting system...");
            isRunning = false;
            return;
        }

        if (index == CommandsIndex.UNKNOWN) {
            System.out.println("Unknown command. Type 'help' to see available commands.");
            return;
        }

        Command command = commandFactory.getCommand(index);
        if (command != null) {
            try {
                String result = command.execute(tokens);
                if (result != null && !result.isEmpty()) {
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Command is recognized but not yet implemented.");//helps if i forget to implement the command
        }
    }
}