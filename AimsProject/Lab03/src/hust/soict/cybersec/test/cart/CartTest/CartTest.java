package hust.soict.cybersec.test.cart.CartTest;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.DuplicateItemException;
import hust.soict.cybersec.aims.media.DigitalVideoDisc2;

import javax.naming.LimitExceededException;

public class CartTest {
    public static void main(String[] args) throws LimitExceededException, DuplicateItemException {
        Cart2 cart = new Cart2();

        cart.addMedia(new DigitalVideoDisc2( "The Lion King",
                "Animation", "Roger Allers", 87, 19.95f));

        cart.addMedia(new DigitalVideoDisc2( "Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f));


        System.out.println("--- Printing Cart Contents ---");
        cart.printCart();
        System.out.println("--------------------------------");

    }
}