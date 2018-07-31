package MainData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;

public class Books {
    private SimpleStringProperty title = new SimpleStringProperty("");
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty author = new SimpleStringProperty("");
    private SimpleStringProperty publisher = new SimpleStringProperty("");
    private SimpleIntegerProperty availability = new SimpleIntegerProperty();

//    public static void main(String[] args) {
//        Books books = new Books(1, "d", "d", "d", 1);
//        System.out.println(books.getId());
//    }

    public Books(int id, String title, String author, String publisher, int availability) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.availability = new SimpleIntegerProperty(availability);
    }

    public Books() {

    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public int getAvailability() {
        return availability.get();
    }

    public SimpleIntegerProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability.set(availability);
    }
}
