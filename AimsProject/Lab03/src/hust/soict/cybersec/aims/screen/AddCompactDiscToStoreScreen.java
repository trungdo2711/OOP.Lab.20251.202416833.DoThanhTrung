package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.media.CompactDisc;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private TextField tfArtist;
    private TextField tfDirector;
    private TextField tfLength;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected String getHeaderText() {
        return "Add CD to Store";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfArtist = createStyledTextField("Artist Name");
        tfDirector = createStyledTextField("Director Name");
        tfLength = createStyledTextField("Length (minutes)");

        addInputField(grid, "Artist:", tfArtist, 3);
        addInputField(grid, "Director:", tfDirector, 4);
        addInputField(grid, "Length:", tfLength, 5);
    }

    @Override
    protected void handleAdd() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String artist = tfArtist.getText().trim();
            String director = tfDirector.getText().trim();

            // Validation
            if (title.isEmpty()) { showErrorAlert("Title cannot be empty!"); return; }
            if (category.isEmpty()) { showErrorAlert("Category cannot be empty!"); return; }
            if (artist.isEmpty()) { showErrorAlert("Artist cannot be empty!"); return; }
            if (director.isEmpty()) { showErrorAlert("Director cannot be empty!"); return; }

            float cost = Float.parseFloat(tfCost.getText());
            int length = Integer.parseInt(tfLength.getText());

            if (cost < 0) { showErrorAlert("Cost cannot be negative!"); return; }
            if (length < 0) { showErrorAlert("Length cannot be negative!"); return; }

            CompactDisc cd = new CompactDisc(title, category, artist, cost, length, director);
            store.addMedia(cd);

            showAlert("CD '" + title + "' has been added successfully!");

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input!\n• Cost must be a number (e.g., 15.50)\n• Length must be an integer (e.g., 60)");
        }
    }
}