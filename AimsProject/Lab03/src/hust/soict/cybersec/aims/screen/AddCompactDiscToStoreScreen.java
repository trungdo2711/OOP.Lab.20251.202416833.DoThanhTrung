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

        addInputField(grid, "Artist:", tfArtist, 3);
        addInputField(grid, "Director:", tfDirector, 4);
        addInputField(grid, "Length:", tfLength, 5);
    }

    @Override
    protected void handleAdd() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String artist = tfArtist.getText();
            String director = tfDirector.getText();

            float cost = Float.parseFloat(tfCost.getText());
            int length = Integer.parseInt(tfLength.getText());

            CompactDisc cd = new CompactDisc(title, category, artist, cost, length, director);
            store.addMedia(cd);

            showAlert("CD '" + title + "' has been added successfully!");

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input!\n• Cost must be a number (e.g., 15.50)\n• Length must be an integer (e.g., 60)");
        }
    }
}