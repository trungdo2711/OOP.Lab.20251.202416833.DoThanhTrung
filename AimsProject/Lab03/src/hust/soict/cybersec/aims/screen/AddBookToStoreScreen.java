package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.media.Book;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private TextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected String getHeaderText() {
        return "Add Book";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfAuthors = new TextField();
        tfAuthors.setPromptText("e.g. Author A, Author B");
        addInputField(grid, "Authors:", tfAuthors, 3);
    }

    @Override
    protected void handleAdd() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());

        Book newBook = new Book(title, category, cost);

        String[] authors = tfAuthors.getText().split(",");
        for (String author : authors) {
            newBook.addAuthor(author.trim());
        }

        store.addMedia(newBook);
        showAlert("Book '" + title + "' added to store successfully!");
    }
}