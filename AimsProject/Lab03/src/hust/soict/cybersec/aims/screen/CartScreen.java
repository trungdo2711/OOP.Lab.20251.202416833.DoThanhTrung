package hust.soict.cybersec.aims.screen;

import java.io.IOException;
import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.screen.controller.CartScreenController;
import hust.soict.cybersec.aims.store.Store.Store;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CartScreen extends Stage {
    private Cart2 cart;
    private Store store;

    public CartScreen(Cart2 cart, Store store) {
        this.cart = cart;
        this.store = store;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/cybersec/aims/screen/view/Cart.fxml"));

            // Define the controller with the cart and store
            loader.setControllerFactory(param -> new CartScreenController(cart, store));

            Parent root = loader.load();
            Scene scene = new Scene(root);

            this.setScene(scene);
            this.setTitle("Cart");
            this.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}