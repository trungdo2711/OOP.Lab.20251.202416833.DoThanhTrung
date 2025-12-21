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
        return "Add Book to Store";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfAuthors = new TextField();
        tfAuthors.setPromptText("Authors (comma separated)");
        addInputField(grid, "Authors:", tfAuthors, 3);
    }

    @Override
    protected void handleAdd() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();

            float cost = Float.parseFloat(tfCost.getText());

            Book book = new Book(title, category, cost);

            store.addMedia(book);
            showAlert("Book added successfully!");

        } catch (NumberFormatException e) {
            showErrorAlert("Cost must be a valid number! (e.g. 12.50)");
        }
    }
}