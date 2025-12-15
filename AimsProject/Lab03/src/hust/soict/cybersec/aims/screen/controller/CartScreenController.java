package hust.soict.cybersec.aims.screen.controller;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.Media;
import hust.soict.cybersec.aims.media.Playable;
import hust.soict.cybersec.aims.screen.StoreScreenFX;
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
import javafx.stage.Stage;

public class CartScreenController {

    private Cart2 cart;
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

    public CartScreenController(Cart2 cart) {
        super();
        this.cart = cart;
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

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia(newValue));

        radioBtnFilterId.selectedProperty().addListener((obs, oldV, newV) -> showFilteredMedia(tfFilter.getText()));
        radioBtnFilterTitle.selectedProperty().addListener((obs, oldV, newV) -> showFilteredMedia(tfFilter.getText()));
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        new StoreScreenFX().show();
        Stage stage = (Stage) costLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) return;

        if (media instanceof Playable) {
            try {
                Alert alert = new Alert(Alert.AlertType.NONE, ((Playable) media).playGUI());
                alert.setTitle("Playing");
                alert.setHeaderText(null);
                alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                alert.showAndWait();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
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
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty! Please add items.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText("Thank you for your order!");
            alert.setContentText("Total Cost: " + String.format("%.2f $", cart.totalCost()));
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
            if (lowerCaseKeyword.isEmpty()) {
                return true;
            }

            if (radioBtnFilterId.isSelected()) {
                try {
                    return String.valueOf(media.getID()).startsWith(lowerCaseKeyword);
                } catch (NumberFormatException e) {
                    return false;
                }
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseKeyword);
            }
            return false;
        });
    }
}