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
        return "Add DVD";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfDirector = new TextField();
        tfLength = new TextField();

        addInputField(grid, "Director:", tfDirector, 3);
        addInputField(grid, "Length (mins):", tfLength, 4);
    }

    @Override
    protected void handleAdd() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String director = tfDirector.getText();
        int length = Integer.parseInt(tfLength.getText());

        DigitalVideoDisc2 newDVD = new DigitalVideoDisc2(title, category, director, length, cost);

        store.addMedia(newDVD);
        showAlert("DVD '" + title + "' added to store successfully!");
    }
}