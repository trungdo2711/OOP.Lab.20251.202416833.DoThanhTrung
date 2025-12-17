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
        // Add to Row 3 (Since 0, 1, 2 are taken by Title, Category, Cost)
        addInputField(grid, "Authors:", tfAuthors, 3);
    }

    @Override
    protected void handleAdd() {
        try {
            // 1. Get Inputs
            String title = tfTitle.getText();
            String category = tfCategory.getText();

            // 2. Parse Numbers (This triggers NumberFormatException)
            float cost = Float.parseFloat(tfCost.getText());

            // 3. Logic
            Book book = new Book(title, category, cost);
            // (Optional: Add author parsing logic here)

            store.addMedia(book);
            showAlert("Book added successfully!");

        } catch (NumberFormatException e) {
            // 4. Use the new Helper method from Parent
            showErrorAlert("Cost must be a valid number! (e.g. 12.50)");
        }
    }
}