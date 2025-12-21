package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.store.Store.Store;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public abstract class AddItemToStoreScreen extends Stage {
    protected Store store;
    protected TextField tfTitle;
    protected TextField tfCategory;
    protected TextField tfCost;

    private static final String ACCENT_COLOR = "#009688";
    private static final String HOVER_COLOR  = "#00796b";
    private static final String BG_COLOR     = "#f4f4f4";
    private static final String TEXT_COLOR   = "#333333";

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        VBox card = new VBox(20);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-radius: 15;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.15));
        card.setEffect(dropShadow);

        Label header = new Label(getHeaderText());
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        header.setTextFill(Color.web(ACCENT_COLOR));
        card.getChildren().add(header);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        tfTitle = createStyledTextField("Enter title");
        tfCategory = createStyledTextField("Enter category");
        tfCost = createStyledTextField("Enter cost (e.g. 15.00)");

        addInputField(grid, "Title:", tfTitle, 0);
        addInputField(grid, "Category:", tfCategory, 1);
        addInputField(grid, "Cost:", tfCost, 2);

        addSpecificFields(grid);

        card.getChildren().add(grid);

        Button btnAdd = new Button("Add Item");
        btnAdd.setPrefWidth(200);
        btnAdd.setPrefHeight(40);
        btnAdd.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        String defaultBtnStyle = "-fx-background-color: " + ACCENT_COLOR + "; -fx-text-fill: white; -fx-background-radius: 25; -fx-cursor: hand;";
        String hoverBtnStyle   = "-fx-background-color: " + HOVER_COLOR + "; -fx-text-fill: white; -fx-background-radius: 25; -fx-cursor: hand;";

        btnAdd.setStyle(defaultBtnStyle);
        btnAdd.setOnMouseEntered(e -> btnAdd.setStyle(hoverBtnStyle));
        btnAdd.setOnMouseExited(e -> btnAdd.setStyle(defaultBtnStyle));

        btnAdd.setOnAction(e -> handleAdd());

        card.getChildren().add(btnAdd);

        StackPane centerStack = new StackPane(card);
        centerStack.setPadding(new Insets(40));
        root.setCenter(centerStack);

        Scene scene = new Scene(root, 550, 600);
        this.setScene(scene);
        this.setTitle(getHeaderText());
        this.setResizable(false);
    }

    private TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(35);
        tf.setPrefWidth(250);
        tf.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5;");

        tf.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                tf.setStyle("-fx-background-color: white; -fx-border-color: " + ACCENT_COLOR + "; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5;");
            } else {
                tf.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5;");
            }
        });

        return tf;
    }

    protected void addInputField(GridPane grid, String labelText, Control inputControl, int row) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
        label.setTextFill(Color.web(TEXT_COLOR));
        grid.add(label, 0, row);
        grid.add(inputControl, 1, row);
    }

    protected abstract String getHeaderText();
    protected abstract void addSpecificFields(GridPane grid);
    protected abstract void handleAdd();

    protected void showAlert(String message) {
        showCustomAlert("Success", "Item Added Successfully", message, true);
    }

    protected void showErrorAlert(String message) {
        showCustomAlert("Input Error", "Invalid Input", message, false);
    }

    private void showCustomAlert(String windowTitle, String headerTitle, String contentMessage, boolean closeWindowOnFinish) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(windowTitle);
        alert.setHeaderText(null);

        Label titleLabel = new Label(headerTitle);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web(ACCENT_COLOR));

        Label messageLabel = new Label(contentMessage);
        messageLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        messageLabel.setTextFill(Color.web(TEXT_COLOR));
        messageLabel.setWrapText(true);

        VBox content = new VBox(10);
        content.getChildren().addAll(titleLabel, messageLabel);
        alert.getDialogPane().setContent(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        if (okButton != null) {
            String defaultStyle = "-fx-background-color: " + ACCENT_COLOR + "; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 20 8 20;";
            String hoverStyle = "-fx-background-color: " + HOVER_COLOR + "; -fx-text-fill: white; -fx-background-radius: 15; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 20 8 20;";

            okButton.setStyle(defaultStyle);
            okButton.setOnMouseEntered(e -> okButton.setStyle(hoverStyle));
            okButton.setOnMouseExited(e -> okButton.setStyle(defaultStyle));
        }

        alert.showAndWait();

        if (closeWindowOnFinish) {
            this.close();
        }
    }
}