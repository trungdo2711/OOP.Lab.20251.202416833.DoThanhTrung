package hust.soict.cybersec.test.store.StoreTest;

import hust.soict.cybersec.aims.disc.DigitalVideoDisc.DigitalVideoDisc2;
import hust.soict.cybersec.aims.store.Store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc2 dvd1 = new DigitalVideoDisc2("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);

        store.addDVD(dvd1);

        DigitalVideoDisc2 dvd2 = new DigitalVideoDisc2("Aladin",
                "Animation", 18.99f);

        store.addDVD(dvd2);

        store.removeDVD(dvd2);
    }
}