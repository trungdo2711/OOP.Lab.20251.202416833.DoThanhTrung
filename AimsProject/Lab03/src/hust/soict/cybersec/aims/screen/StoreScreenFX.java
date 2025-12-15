package hust.soict.cybersec.aims.screen;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.*;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

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
        DigitalVideoDisc2 dvd1 = new DigitalVideoDisc2("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc2 dvd2 = new DigitalVideoDisc2("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc2 dvd3 = new DigitalVideoDisc2("Aladin", "Animation", 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        Book book1 = new Book("The Age of Innocence", "Literature", 12.50f);
        Book book2 = new Book("Snow Crash", "Science Fiction", 9.99f);
        Book book3 = new Book("And Then There Were None", "Mystery", 8.75f);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        CompactDisc cd1 = new CompactDisc("Retro Future", "Synthwave", "Neon Rider", 18.99f, 0, "None");
        cd1.addTrack(new Track("Grid Runners", 285));

        CompactDisc cd2 = new CompactDisc("Echoes in the Canyon", "Folk Rock", "The Wanderer Collective", 12.50f, 0, "None");
        cd2.addTrack(new Track("Dusty Road Blues", 205));
        store.addMedia(cd1);
        store.addMedia(cd2);
    }

    public void show() {
        Stage primaryStage = new Stage();
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(new NorthVBox(store, cart));
        root.setCenter(new CenterFlowPane(store, cart));

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class NorthVBox extends VBox {
        public NorthVBox(Store store, Cart2 cart) {
            this.getChildren().addAll(new StoreMenuBar(store), new StoreHeader(cart));
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
            itemViewCart.setOnAction(e -> new CartScreen(cart).show());

            menuOptions.getItems().addAll(itemViewStore, itemViewCart);
            this.getMenus().add(menuOptions);
        }
    }

    private static class StoreHeader extends HBox {
        public StoreHeader(Cart2 cart) {
            this.setSpacing(10);
            this.setPadding(new javafx.geometry.Insets(10));
            this.setAlignment(Pos.CENTER_LEFT);

            Text title = new Text("AIMS");
            title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 50));
            title.setFill(Color.CYAN);

            Button btnViewCart = new Button("View Cart");
            btnViewCart.setPrefSize(100, 50);
            btnViewCart.setOnAction(e -> new CartScreen(cart).show());

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
            this.setHgap(10);
            this.setVgap(10);
            this.setPadding(new javafx.geometry.Insets(10));
            this.setAlignment(Pos.TOP_LEFT);

            List<Media> items = store.getItemsInStore();
            for (Media media : items) {
                this.getChildren().add(new MediaStoreVBox(media, cart));
            }
        }
    }

    private static class MediaStoreVBox extends VBox {
        public MediaStoreVBox(Media media, Cart2 cart) {
            this.setSpacing(5);
            this.setPadding(new javafx.geometry.Insets(5));
            this.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
            this.setPrefWidth(200);
            this.setAlignment(Pos.TOP_CENTER);

            Label title = new Label(media.getTitle());
            title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
            title.setAlignment(Pos.CENTER);

            Label cost = new Label(String.format("%.2f $", media.getCost()));
            cost.setAlignment(Pos.CENTER);

            HBox container = new HBox(10);
            container.setAlignment(Pos.CENTER);

            Button btnAddToCart = new Button("Add to Cart");
            btnAddToCart.setOnAction(e -> {
                cart.addMedia(media);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cart Update");
                alert.setHeaderText(null);
                alert.setContentText(media.getTitle() + " has been added to the cart.");
                alert.showAndWait();
            });

            if (media instanceof Playable) {
                Button btnPlay = new Button("Play");
                btnPlay.setOnAction(e -> {
                    try {
                        showPlayDialog(media);
                    } catch (PlayerException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    }
                });
                container.getChildren().addAll(btnAddToCart, btnPlay);
            } else {
                container.getChildren().add(btnAddToCart);
            }

            this.getChildren().addAll(title, cost, container);
        }

        private void showPlayDialog(Media media) throws PlayerException {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Playing Media");
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
                    info.append("- ").append(track.getTitle()).append(" (").append(track.getLength()).append("m)\n");
                }
            }

            Label content = new Label(info.toString());
            content.setFont(Font.font("Consolas", FontWeight.NORMAL, 14));
            content.setWrapText(true);

            ScrollPane scroll = new ScrollPane(content);
            scroll.setFitToWidth(true);
            scroll.setPrefSize(400, 200);

            dialog.getDialogPane().setContent(scroll);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();
        }
    }
}