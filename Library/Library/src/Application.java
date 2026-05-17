import core.Engine;
import data.FileService;
import data.LibrarySystem;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;

/**
 * Entry point for the Library Management System.
 *
 * <p>Wires together the {@link LibraryData} and {@link FileActions} implementations
 * and hands control to the {@link Engine}.
 */
public class Application {
    public static void main(String[] args) {
        LibraryData libraryData = new LibrarySystem();
        FileActions fileActions = new FileService();
        Engine engine = new Engine(libraryData, fileActions);
        engine.run();
    }
}