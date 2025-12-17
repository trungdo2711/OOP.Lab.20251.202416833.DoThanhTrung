package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.DuplicateItemException;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.*;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.naming.LimitExceededException;

public class StoreScreenFX extends Application {

    private static Store store = new Store();
    private static Cart2 cart = new Cart2();

    public StoreScreenFX() {
    }

    public StoreScreenFX(Store store) {
        this.store = store;
    }

    public static void main(String[] args) {
        initSetup();
        launch(args);
    }

    private static void initSetup() {
        if (store.getItemsInStore().isEmpty()) {
            store.addMedia(new DigitalVideoDisc2("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
            store.addMedia(new DigitalVideoDisc2("Star Wars", "Sci-Fi", "George Lucas", 87, 24.95f));
            store.addMedia(new DigitalVideoDisc2("Aladin", "Animation", 18.99f));
            store.addMedia(new DigitalVideoDisc2("Inception", "Sci-Fi", "Christopher Nolan", 148, 15.50f));
            store.addMedia(new DigitalVideoDisc2("The Matrix", "Sci-Fi", "Wachowskis", 136, 12.99f));
            store.addMedia(new DigitalVideoDisc2("Interstellar", "Sci-Fi", "Christopher Nolan", 169, 18.00f));
            store.addMedia(new DigitalVideoDisc2("Frozen", "Animation", "Chris Buck", 102, 20.00f));
            store.addMedia(new DigitalVideoDisc2("Avatar", "Sci-Fi", "James Cameron", 162, 22.50f));
            store.addMedia(new DigitalVideoDisc2("Titanic", "Romance", "James Cameron", 195, 18.50f));
            store.addMedia(new DigitalVideoDisc2("The Avengers", "Action", "Joss Whedon", 143, 21.00f));
            store.addMedia(new DigitalVideoDisc2("Up", "Animation", "Pete Docter", 96, 15.00f));
            store.addMedia(new DigitalVideoDisc2("Wall-E", "Animation", "Andrew Stanton", 98, 16.00f));

            // --- BOOKS ---
            store.addMedia(new Book("The Age of Innocence", "Literature", 12.50f));
            store.addMedia(new Book("Snow Crash", "Sci-Fi", 9.99f));
            store.addMedia(new Book("Effective Java", "Programming", 45.00f));
            store.addMedia(new Book("Clean Code", "Programming", 40.00f));
            store.addMedia(new Book("Thinking in Java", "Programming", 35.00f));
            store.addMedia(new Book("Design Patterns", "Programming", 50.00f));
            store.addMedia(new Book("The Great Gatsby", "Literature", 10.00f));
            store.addMedia(new Book("1984", "Sci-Fi", 8.50f));
            store.addMedia(new Book("Brave New World", "Sci-Fi", 9.00f));
            store.addMedia(new Book("Head First Java", "Programming", 28.00f));

            // --- CDs ---
            CompactDisc cd1 = new CompactDisc("Retro Future", "Synthwave", "Neon Rider", 18.99f, 0, "None");
            cd1.addTrack(new Track("Grid Runners", 285));
            cd1.addTrack(new Track("Cyber City", 300));
            store.addMedia(cd1);

            CompactDisc cd2 = new CompactDisc("Thriller", "Pop", "Michael Jackson", 15.00f, 0, "None");
            cd2.addTrack(new Track("Thriller", 357));
            cd2.addTrack(new Track("Beat It", 258));
            cd2.addTrack(new Track("Billie Jean", 294));
            store.addMedia(cd2);

            CompactDisc cd3 = new CompactDisc("Back in Black", "Rock", "AC/DC", 14.50f, 0, "None");
            cd3.addTrack(new Track("Hells Bells", 312));
            cd3.addTrack(new Track("Back in Black", 255));
            store.addMedia(cd3);

            CompactDisc cd4 = new CompactDisc("Abbey Road", "Rock", "The Beatles", 25.00f, 0, "None");
            cd4.addTrack(new Track("Come Together", 259));
            cd4.addTrack(new Track("Something", 183));
            store.addMedia(cd4);

            // --- ERROR TESTING ---
            store.addMedia(new DigitalVideoDisc2("Broken Movie", "Error", "Unknown", 0, 5.00f));
        }
    }

    public void show() {
        try {
            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(new NorthVBox(store, cart));

        ScrollPane scrollPane = new ScrollPane(new CenterFlowPane(store, cart));
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1024, 768);
        try {
            scene.getStylesheets().add(getClass().getResource("/hust/soict/cybersec/aims/screen/view/storestyle.css").toExternalForm());
        } catch (Exception e) {
            // Warning suppressed for cleaner console
        }

        primaryStage.setTitle("Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class NorthVBox extends VBox {
        public NorthVBox(Store store, Cart2 cart) {
            this.getChildren().addAll(new StoreMenuBar(store), new StoreHeader(cart, store));
        }
    }

    private static class StoreMenuBar extends MenuBar {
        public StoreMenuBar(Store store) {
            Menu menuOptions = new Menu("Options");
            Menu menuUpdateStore = new Menu("Update Store");

            MenuItem itemAddBook = new MenuItem("Add Book");
            itemAddBook.setOnAction(e -> new AddBookToStoreScreen(store).show());

            MenuItem itemAddCD = new MenuItem("Add CD");
            itemAddCD.setOnAction(e -> new AddCompactDiscToStoreScreen(store).show());

            MenuItem itemAddDVD = new MenuItem("Add DVD");
            itemAddDVD.setOnAction(e -> new AddDigitalVideoDiscToStoreScreen(store).show());

            menuUpdateStore.getItems().addAll(itemAddBook, itemAddCD, itemAddDVD);
            menuOptions.getItems().add(menuUpdateStore);

            MenuItem itemViewStore = new MenuItem("View Store");
            itemViewStore.setOnAction(e -> new StoreScreenFX(store).show());

            MenuItem itemViewCart = new MenuItem("View Cart");
            itemViewCart.setOnAction(e -> new CartScreen(cart, store));

            menuOptions.getItems().addAll(itemViewStore, itemViewCart);
            this.getMenus().add(menuOptions);
        }
    }

    private static class StoreHeader extends HBox {
        public StoreHeader(Cart2 cart, Store store) {
            this.setSpacing(10);
            this.setPadding(new Insets(10));
            this.setAlignment(Pos.CENTER_LEFT);

            Label title = new Label("AIMS");
            title.getStyleClass().add("label-header");

            Button btnViewCart = new Button("View Cart");
            btnViewCart.setPrefSize(100, 50);
            btnViewCart.getStyleClass().add("button");

            btnViewCart.setOnAction(e -> {
                new CartScreen(cart, store);
            });

            Region rigidLeft = new Region();
            rigidLeft.setPrefSize(10, 10);
            Region glue = new Region();
            HBox.setHgrow(glue, Priority.ALWAYS);
            Region rigidRight = new Region();
            rigidRight.setPrefSize(10, 10);

            this.getChildren().addAll(rigidLeft, title, glue, btnViewCart, rigidRight);
        }
    }

    private static class CenterFlowPane extends FlowPane {
        public CenterFlowPane(Store store, Cart2 cart) {
            this.setHgap(20);
            this.setVgap(20);
            this.setPadding(new Insets(20));
            this.setAlignment(Pos.TOP_LEFT);
            this.setStyle("-fx-background-color: transparent;");

            for (Media media : store.getItemsInStore()) {
                this.getChildren().add(new MediaStoreVBox(media, cart));
            }
        }
    }

    private static class MediaStoreVBox extends VBox {
        public MediaStoreVBox(Media media, Cart2 cart) {
            this.getStyleClass().add("media-card");
            this.setPrefWidth(300);
            this.setSpacing(10);
            this.setAlignment(Pos.CENTER);

            Label title = new Label(media.getTitle());
            title.setWrapText(true);
            title.setAlignment(Pos.CENTER);
            title.getStyleClass().add("media-title");

            Label cost = new Label(String.format("%.2f $", media.getCost()));
            cost.setAlignment(Pos.CENTER);
            cost.getStyleClass().add("label-cost");

            HBox container = new HBox(15);
            container.setAlignment(Pos.CENTER);

            Button btnAddToCart = new Button("Add to Cart");
            btnAddToCart.getStyleClass().add("button");

            btnAddToCart.setOnAction(e -> {
                try {
                    cart.addMedia(media);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cart Update");
                    alert.setHeaderText("Success");
                    alert.setContentText(media.getTitle() + " has been added to the cart.");
                    styleAlert(alert);
                    alert.showAndWait();

                } catch (LimitExceededException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cart Full");
                    alert.setHeaderText("Limit Reached");
                    alert.setContentText(ex.getMessage());
                    styleAlert(alert);
                    alert.showAndWait();

                } catch (DuplicateItemException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cart Update");
                    alert.setHeaderText("Duplicate Item");
                    alert.setContentText(ex.getMessage());
                    styleAlert(alert);
                    alert.showAndWait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            if (media instanceof Playable) {
                Button btnPlay = new Button("Play");
                btnPlay.getStyleClass().add("button");

                btnPlay.setOnAction(e -> {
                    try {
                        ((Playable) media).play();

                        showPlayDialog(media);

                    } catch (PlayerException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Playback Error");
                        alert.setContentText(ex.getMessage());
                        styleAlert(alert);
                        alert.showAndWait();
                    }
                });
                container.getChildren().addAll(btnAddToCart, btnPlay);
            } else {
                container.getChildren().add(btnAddToCart);
            }

            this.getChildren().addAll(title, cost, container);
        }
        private void styleAlert(Alert alert) {
            DialogPane dialogPane = alert.getDialogPane();
            try {
                dialogPane.getStylesheets().add(getClass().getResource("/hust/soict/cybersec/aims/screen/view/storestyle.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
            } catch (Exception ex) {
                // Ignore missing CSS
            }
        }

        private void showPlayDialog(Media media) throws PlayerException {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("UPDATED PLAYER"); // Verify title updated
            dialog.setHeaderText("Now Playing: " + media.getTitle());

            StringBuilder info = new StringBuilder();
            info.append("Title:    ").append(media.getTitle()).append("\n");
            info.append("Category: ").append(media.getCategory()).append("\n");

            if (media instanceof DigitalVideoDisc2) {
                DigitalVideoDisc2 dvd = (DigitalVideoDisc2) media;
                info.append("Director: ").append(dvd.getDirector()).append("\n");
                info.append("Length:   ").append(dvd.getLength()).append(" mins\n");

            } else if (media instanceof CompactDisc) {
                CompactDisc cd = (CompactDisc) media;
                info.append("Artist:   ").append(cd.getArtist()).append("\n");
                info.append("Length:   ").append(cd.getLength()).append(" mins\n");
                info.append("\nTracks:\n");
                for (Track track : cd.getTracks()) {
                    info.append(" • ").append(track.getTitle()).append(" (").append(track.getLength()).append("m)\n");
                }
            }

            Label content = new Label(info.toString());
            content.setWrapText(true);
            content.setStyle("-fx-font-family: 'Consolas', monospace; -fx-font-size: 14px;");

            VBox contentBox = new VBox(content);
            contentBox.setPadding(new Insets(10));

            ScrollPane scroll = new ScrollPane(contentBox);
            scroll.setFitToWidth(true);
            scroll.setPrefSize(400, 300);
            scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

            dialog.getDialogPane().setContent(scroll);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

            try {
                dialog.getDialogPane().getStylesheets().add(
                        getClass().getResource("/hust/soict/cybersec/aims/screen/view/storestyle.css").toExternalForm()
                );
                dialog.getDialogPane().getStyleClass().add("dialog-pane");
            } catch (Exception e) {
                // Ignore CSS error
            }

            dialog.showAndWait();
        }
    }
}