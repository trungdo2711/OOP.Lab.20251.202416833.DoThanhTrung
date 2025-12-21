package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.media.DigitalVideoDisc2;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private TextField tfDirector;
    private TextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected String getHeaderText() {
        return "Add DVD to Store";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfDirector = new TextField();
        tfDirector.setPromptText("Director Name");

        tfLength = new TextField();
        tfLength.setPromptText("Length (minutes)");

        // Add specific fields starting from Row 3
        addInputField(grid, "Director:", tfDirector, 3);
        addInputField(grid, "Length:", tfLength, 4);
    }

    @Override
    protected void handleAdd() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String director = tfDirector.getText();

            float cost = Float.parseFloat(tfCost.getText());
            int length = Integer.parseInt(tfLength.getText());

            DigitalVideoDisc2 dvd = new DigitalVideoDisc2(title, category, director, length, cost);
            store.addMedia(dvd);

            showAlert("DVD '" + title + "' has been added successfully!");

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input!\n• Cost must be a number (e.g., 19.95)\n• Length must be an integer (e.g., 120)");
        }
    }
}