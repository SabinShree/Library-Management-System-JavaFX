package ListAdder;

import MainData.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//Singleton class.
public class LibraryFileSystem {
    private static LibraryFileSystem libraryFileSystem = new LibraryFileSystem();

    private ObservableList<Books> observableList = FXCollections.observableArrayList();

    public ObservableList<Books> getObservableList() {
        return observableList;
    }

    public static LibraryFileSystem getLibraryFileSystem() {
        return libraryFileSystem;
    }


    public void addBooksToTable(Books books) {
        this.observableList.add(books);
    }

}
