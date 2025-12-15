package hust.soict.cybersec.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NumberGrid extends Application {

    private TextField tfDisplay;

    private void handleButtonAction(javafx.event.ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText();
        String currentText = tfDisplay.getText();

        if (buttonText.length() == 1 && buttonText.charAt(0) >= '0' && buttonText.charAt(0) <= '9') {
            tfDisplay.setText(currentText + buttonText);
        } else if (buttonText.equals("DEL")) {
            if (!currentText.isEmpty()) {
                tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else if (buttonText.equals("C")) {
            tfDisplay.setText("");
        }
    }

    private GridPane createButtonPanel() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        Button[] btnNumbers = new Button[10];

        java.util.function.Function<String, Button> createAndConfigureButton = (text) -> {
            Button btn = new Button(text);
            btn.setPrefSize(50, 50);
            btn.setOnAction(this::handleButtonAction);
            return btn;
        };

        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = createAndConfigureButton.apply(String.valueOf(i));

            int row;
            if (i >= 7) row = 0;
            else if (i >= 4) row = 1;
            else row = 2;

            int col = (i - 1) % 3;

            gridPane.add(btnNumbers[i], col, row);
        }

        btnNumbers[0] = createAndConfigureButton.apply("0");
        gridPane.add(btnNumbers[0], 1, 3);

        Button btnDelete = createAndConfigureButton.apply("DEL");
        gridPane.add(btnDelete, 0, 3);

        Button btnReset = createAndConfigureButton.apply("C");
        gridPane.add(btnReset, 2, 3);

        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) {
        tfDisplay = new TextField();
        tfDisplay.setAlignment(Pos.CENTER_RIGHT);
        tfDisplay.setEditable(false);
        tfDisplay.setPrefHeight(50);

        GridPane panelButtons = createButtonPanel();

        BorderPane root = new BorderPane();

        root.setTop(tfDisplay);
        root.setCenter(panelButtons);

        root.setStyle("-fx-padding: 10;");

        Scene scene = new Scene(root);

        primaryStage.setTitle("Number Grid");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}