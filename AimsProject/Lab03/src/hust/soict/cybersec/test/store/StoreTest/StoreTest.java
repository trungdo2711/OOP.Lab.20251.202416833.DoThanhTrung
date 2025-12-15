package hust.soict.cybersec.test.store.StoreTest;

import hust.soict.cybersec.aims.media.DigitalVideoDisc2;
import hust.soict.cybersec.aims.store.Store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc2 dvd1 = new DigitalVideoDisc2( "The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);

        store.addMedia(dvd1);
    }
}