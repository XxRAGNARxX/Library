package commands;

import exceptions.CommandException;


/**
 * Contract for all CLI commands in the Library Management System.
 *
 * <p>Every command must be able to execute itself given the raw tokenised
 * input array and produce a human-readable result string, as well as
 * expose a one-line help message used by the {@code help} command.
 */
public interface Command {
    String execute(String[] args);
    String getHelpMessage();
}