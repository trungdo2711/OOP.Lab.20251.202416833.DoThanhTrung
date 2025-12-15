package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.media.CompactDisc;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private TextField tfArtist;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    protected String getHeaderText() {
        return "Add CD";
    }

    @Override
    protected void addSpecificFields(GridPane grid) {
        tfArtist = new TextField();
        addInputField(grid, "Artist:", tfArtist, 3);
    }

    @Override
    protected void handleAdd() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String artist = tfArtist.getText();

        CompactDisc newCD = new CompactDisc(title, category, artist, cost, 0, "");

        store.addMedia(newCD);
        showAlert("CD '" + title + "' added to store successfully!");
    }
}