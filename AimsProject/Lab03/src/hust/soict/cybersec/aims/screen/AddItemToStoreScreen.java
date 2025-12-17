package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.store.Store.Store;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public abstract class AddItemToStoreScreen extends Stage {
    protected Store store;
    protected TextField tfTitle;
    protected TextField tfCategory;
    protected TextField tfCost;

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label header = new Label(getHeaderText());
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        header.setTextFill(Color.web("#d32f2f"));
        root.getChildren().add(header);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        tfTitle = new TextField();
        tfCategory = new TextField();
        tfCost = new TextField();

        // Row 0, 1, 2 are standard fields
        addInputField(grid, "Title:", tfTitle, 0);
        addInputField(grid, "Category:", tfCategory, 1);
        addInputField(grid, "Cost:", tfCost, 2);

        // Child classes will add their fields starting at Row 3
        addSpecificFields(grid);

        root.getChildren().add(grid);

        Button btnAdd = new Button("Add Item");
        btnAdd.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAdd.setOnAction(e -> handleAdd());

        root.getChildren().add(btnAdd);

        Scene scene = new Scene(root, 400, 400);
        this.setScene(scene);
        this.setTitle(getHeaderText());
    }

    protected void addInputField(GridPane grid, String labelText, Control inputControl, int row) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        grid.add(label, 0, row);
        grid.add(inputControl, 1, row);
    }

    // Abstract methods to be implemented by Child Classes
    protected abstract String getHeaderText();
    protected abstract void addSpecificFields(GridPane grid);
    protected abstract void handleAdd();

    // Success Alert
    protected void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        this.close();
    }

    // --- NEW: Error Alert Helper for Exceptions ---
    protected void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Requirement: Information Alert for Input Errors
        alert.setTitle("Input Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}