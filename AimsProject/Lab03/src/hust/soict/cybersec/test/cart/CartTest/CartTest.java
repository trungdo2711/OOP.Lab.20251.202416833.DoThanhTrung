package hust.soict.cybersec.test.cart.CartTest;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.disc.DigitalVideoDisc.DigitalVideoDisc2;

public class CartTest {
    public static void main(String[] args) {
        Cart2 cart = new Cart2();

        cart.addDigitalVideoDisc(new DigitalVideoDisc2("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f));

        cart.addDigitalVideoDisc(new DigitalVideoDisc2("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f));

        cart.addDigitalVideoDisc(new DigitalVideoDisc2("Aladin",
                "Animation", 18.99f));

        System.out.println("--- Printing Cart Contents ---");
        cart.printCart();
        System.out.println("--------------------------------");

        System.out.println("\n--- Testing Search ---");

        cart.searchForDVDByTitle("Star Wars");
        cart.searchForDVDByTitle("Frozen");
        cart.searchForDVDByTitle("Aladin");
        System.out.println("----------------------");
    }
}