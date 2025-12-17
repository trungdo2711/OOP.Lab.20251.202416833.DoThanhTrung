package hust.soict.cybersec.aims.screen.controller;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.*;
import hust.soict.cybersec.aims.screen.StoreScreenFX;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class CartScreenController {

    private Cart2 cart;
    private Store store;
    private ObservableList<Media> displayedItems;
    private FilteredList<Media> filteredItems;

    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediacategory;
    @FXML private TableView<Media> tblMedia;
    @FXML private ToggleGroup filterCategory;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private Label costLabel;
    @FXML private TextField tfFilter;
    @FXML private Button placeOrder;

    public CartScreenController(Cart2 cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
    }

    @FXML
    void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        displayedItems = FXCollections.observableArrayList(cart.getItemsOrdered());
        filteredItems = new FilteredList<>(displayedItems, m -> true);
        tblMedia.setItems(filteredItems);

        updateCostLabel();
        btnPlay.setVisible(true);
        btnRemove.setVisible(true);
        btnPlay.setDisable(true);
        btnRemove.setDisable(true);

        // Apply CSS classes
        btnRemove.getStyleClass().add("button-danger");
        costLabel.getStyleClass().add("label-cost");
        placeOrder.getStyleClass().add("button");

        // Table Selection Logic
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        } else {
                            btnPlay.setDisable(true);
                            btnRemove.setDisable(true);
                        }
                    }

                    private void updateButtonBar(Media media) {
                        btnRemove.setDisable(false);
                        if (media instanceof Playable) {
                            btnPlay.setDisable(false);
                        } else {
                            btnPlay.setDisable(true);
                        }
                    }
                }
        );

        // Filter Logic
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia(newValue));
        radioBtnFilterId.selectedProperty().addListener((obs, oldV, newV) -> showFilteredMedia(tfFilter.getText()));
        radioBtnFilterTitle.selectedProperty().addListener((obs, oldV, newV) -> showFilteredMedia(tfFilter.getText()));
    }

    // --- HELPER TO STYLE ALERTS ---
    private void setStyle(Dialog<?> dialog) {
        DialogPane dialogPane = dialog.getDialogPane();
        try {
            dialogPane.getStylesheets().add(
                    getClass().getResource("/hust/soict/cybersec/aims/screen/view/storestyle.css").toExternalForm()
            );
            dialogPane.getStyleClass().add("dialog-pane");
        } catch (Exception e) {
            System.out.println("Warning: Could not load CSS for Cart Dialog.");
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        new StoreScreenFX(store).show();
        Stage stage = (Stage) costLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) return;

        if (media instanceof Playable) {
            // 1. VALIDATE LENGTH
            int len = 0;
            if (media instanceof DigitalVideoDisc2) {
                len = ((DigitalVideoDisc2) media).getLength();
            } else if (media instanceof CompactDisc) {
                len = ((CompactDisc) media).getLength();
            }

            if (len <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Playback Error");
                alert.setContentText("ERROR: " + media.getTitle() + " has invalid length (" + len + ")!");
                setStyle(alert);
                alert.showAndWait();
                return;
            }

            // 2. PLAY WITH FULL INFO
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText("Now Playing: " + media.getTitle());

                StringBuilder info = new StringBuilder();
                info.append("Title:    ").append(media.getTitle()).append("\n");
                info.append("Category: ").append(media.getCategory()).append("\n");

                if (media instanceof DigitalVideoDisc2) {
                    DigitalVideoDisc2 dvd = (DigitalVideoDisc2) media;
                    info.append("Director: ").append(dvd.getDirector()).append("\n");
                    // --- FIXED: ADDED DVD LENGTH ---
                    info.append("Length:   ").append(dvd.getLength()).append(" mins");

                } else if (media instanceof CompactDisc) {
                    CompactDisc cd = (CompactDisc) media;
                    info.append("Artist:   ").append(cd.getArtist()).append("\n");
                    info.append("Length:   ").append(cd.getLength()).append(" mins\n");
                    info.append("Tracks:\n");
                    for (Track track : cd.getTracks()) {
                        info.append(" - ").append(track.getTitle()).append(" (").append(track.getLength()).append("m)\n");
                    }
                }

                // Use label for consistent styling with Store
                Label label = new Label(info.toString());
                label.setWrapText(true);
                label.setStyle("-fx-font-family: 'Consolas', monospace; -fx-font-size: 14px;");

                alert.getDialogPane().setContent(label);

                setStyle(alert);
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Playback Error");
                alert.setContentText(e.getMessage());
                setStyle(alert);
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            displayedItems.remove(media);
            updateCostLabel();
        }
    }

    @FXML
    void placeOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Your cart is empty! Please add items.");
            setStyle(alert);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Total Cost: " + String.format("%.2f $", cart.totalCost()));
            alert.setTitle("Order Placed");
            alert.setHeaderText("Thank you for your order!");

            setStyle(alert);
            alert.showAndWait();

            cart.getItemsOrdered().clear();
            displayedItems.clear();
            updateCostLabel();
        }
    }

    private void updateCostLabel() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void showFilteredMedia(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        filteredItems.setPredicate(media -> {
            if (lowerCaseKeyword.isEmpty()) return true;
            if (radioBtnFilterId.isSelected()) {
                try { return String.valueOf(media.getID()).startsWith(lowerCaseKeyword); }
                catch (NumberFormatException e) { return false; }
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseKeyword);
            }
            return false;
        });
    }
}