package hust.soict.cybersec.aims;

import hust.soict.cybersec.aims.disc.DigitalVideoDisc.DigitalVideoDisc2;

public class Aims2 {
    public static void main(String[] args) {
        Cart2 anOrder = new Cart2();

        DigitalVideoDisc2 dvd1 = new DigitalVideoDisc2("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc2 dvd2 = new DigitalVideoDisc2("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        anOrder.removeDigitalVideoDisc(dvd1);

        DigitalVideoDisc2 dvd3 = new DigitalVideoDisc2("Animation", "Aladin", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        System.out.printf("Total Cost is: %.2f%n", anOrder.totalCost());

        System.out.println("\n--- Cart Contents ---");
        anOrder.printCart();
    }
}