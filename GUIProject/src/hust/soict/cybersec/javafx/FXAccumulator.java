package hust.soict.cybersec.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXAccumulator extends Application {

    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    @Override
    public void start(Stage primaryStage) {

        tfInput = new TextField();

        tfOutput = new TextField();
        tfOutput.setEditable(false);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);


        root.add(new Label("Enter an Integer: "), 0, 0);
        root.add(tfInput, 1, 0);

        // Row 1
        root.add(new Label("The Accumulated Sum is: "), 0, 1);
        root.add(tfOutput, 1, 1);


        tfInput.setOnAction(e -> {
            try {
                int numberIn = Integer.parseInt(tfInput.getText());
                sum += numberIn;

                // Update UI elements
                tfOutput.setText(String.valueOf(sum));
                tfInput.setText("");

            } catch (NumberFormatException ex) {
                System.err.println("Invalid input: Please enter a valid integer.");
                tfInput.setText("");
            }
        });

        Scene scene = new Scene(root, 350, 120);
        primaryStage.setTitle("JavaFX Accumulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}