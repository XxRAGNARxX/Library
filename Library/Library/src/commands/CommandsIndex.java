package commands;

public enum CommandsIndex {
    HELP,
    EXIT,
    LOGIN,
    LOGOUT,
    OPEN,
    CLOSE,
    SAVE,
    SAVE_AS,
    BOOKS_ALL,
    BOOKS_INFO,
    BOOKS_FIND,
    BOOKS_SORT,
    BOOKS_ADD,
    BOOKS_REMOVE,
    USERS_ADD,
    USERS_REMOVE,
    UNKNOWN;

    public static CommandsIndex fromString(String input) {
        return switch (input.toLowerCase()) {
            case "help" -> HELP;
            case "exit" -> EXIT;
            case "login" -> LOGIN;
            case "logout" -> LOGOUT;
            case "open" -> OPEN;
            case "close" -> CLOSE;
            case "save" -> SAVE;
            case "save as", "saveas" -> SAVE_AS;
            case "books all" -> BOOKS_ALL;
            case "books info", "books view" -> BOOKS_INFO;
            case "books find" -> BOOKS_FIND;
            case "books sort" -> BOOKS_SORT;
            case "books add" -> BOOKS_ADD;
            case "books remove" -> BOOKS_REMOVE;
            case "users add" -> USERS_ADD;
            case "users remove" -> USERS_REMOVE;
            default -> UNKNOWN;
        };
    }
}