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
        // Use the protected helper method for consistent styling
        tfAuthors = createStyledTextField("Authors (comma separated)");
        addInputField(grid, "Authors:", tfAuthors, 3);
    }

    @Override
    protected void handleAdd() {
        try {
            // 1. Get inputs
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();

            // 2. Validate Empty Fields
            if (title.isEmpty()) {
                showErrorAlert("Title cannot be empty!");
                return;
            }
            if (category.isEmpty()) {
                showErrorAlert("Category cannot be empty!");
                return;
            }

            // 3. Parse and Validate Cost
            float cost = Float.parseFloat(tfCost.getText());
            if (cost < 0) {
                showErrorAlert("Cost cannot be negative!");
                return;
            }

            // 4. Create Book
            Book book = new Book(title, category, cost);

            // Optional: Add Authors if present
            String authorsText = tfAuthors.getText().trim();
            if (!authorsText.isEmpty()) {
                for (String author : authorsText.split(",")) {
                    book.addAuthor(author.trim());
                }
            }

            store.addMedia(book);
            showAlert("Book added successfully!");

        } catch (NumberFormatException e) {
            showErrorAlert("Cost must be a valid number! (e.g. 12.50)");
        }
    }
}