package hust.soict.cybersec.aims.screen;

import java.io.IOException;
import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.screen.controller.CartScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CartScreen extends Stage {

    private Cart2 cart;

    public CartScreen(Cart2 cart) {
        this.cart = cart;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/cybersec/aims/screen/view/Cart.fxml"));

            CartScreenController controller = new CartScreenController(cart);
            loader.setController(controller);

            Parent root = loader.load();

            this.setTitle("Cart");
            this.setScene(new Scene(root));
            this.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}