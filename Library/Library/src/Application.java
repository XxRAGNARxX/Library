import core.Engine;
import data.FileService;
import data.LibrarySystem;
import data.interfaces.FileActions;
import data.interfaces.LibraryData;

public class Application {
    public static void main(String[] args) {
        LibraryData libraryData = new LibrarySystem();
        FileActions fileActions = new FileService();
        Engine engine = new Engine(libraryData, fileActions);
        engine.run();
    }
}
