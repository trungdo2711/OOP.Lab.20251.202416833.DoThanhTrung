package hust.soict.cybersec.aims.store.Store;

import hust.soict.cybersec.aims.disc.DigitalVideoDisc.DigitalVideoDisc2;

import java.util.ArrayList;

public class Store{
    private ArrayList<DigitalVideoDisc2> itemsInStore;

    public Store(){
        this.itemsInStore = new ArrayList<>();
    }

    public void addDVD(DigitalVideoDisc2 dvd){
        this.itemsInStore.add(dvd);
        System.out.println("The disc " + dvd.getTitle() + " has been added to the store. ");
    }

    public void removeDVD(DigitalVideoDisc2 dvd){
        if (itemsInStore.remove(dvd)){
            System.out.println("The disc " + dvd.getTitle() + " has been removed from the store");
        }
        else{
            System.out.println("The dvd " + dvd.getTitle() + " is not in the store.");
        }
    }

}