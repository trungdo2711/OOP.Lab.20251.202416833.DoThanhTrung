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
        tfArtist = new TextField();
        tfArtist.setPromptText("Artist Name");

        tfDirector = new TextField();
        tfDirector.setPromptText("Director Name");

        tfLength = new TextField();
        tfLength.setPromptText("Length (minutes)");

        // Add specific fields starting from Row 3 (Rows 0-2 are Title, Category, Cost)
        addInputField(grid, "Artist:", tfArtist, 3);
        addInputField(grid, "Director:", tfDirector, 4);
        addInputField(grid, "Length:", tfLength, 5);
    }

    @Override
    protected void handleAdd() {
        try {
            // 1. Get Text Inputs
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String artist = tfArtist.getText();
            String director = tfDirector.getText();

            // 2. Parse Numeric Inputs (catches NumberFormatException)
            float cost = Float.parseFloat(tfCost.getText());
            int length = Integer.parseInt(tfLength.getText());

            // 3. Create and Add CD
            CompactDisc cd = new CompactDisc(title, category, artist, cost, length, director);
            store.addMedia(cd);

            // 4. Show Success
            showAlert("CD '" + title + "' has been added successfully!");

        } catch (NumberFormatException e) {
            // 5. Handle Invalid Numbers
            showErrorAlert("Invalid Input!\n• Cost must be a number (e.g., 15.50)\n• Length must be an integer (e.g., 60)");
        }
    }
}