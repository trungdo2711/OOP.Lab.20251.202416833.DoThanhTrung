package hust.soict.cybersec.aims.store.Store;

import hust.soict.cybersec.aims.media.DigitalVideoDisc2;
import hust.soict.cybersec.aims.media.Media;

import java.util.ArrayList;

public class Store{
    private ArrayList<Media> itemsInStore;

    public Store(){
        this.itemsInStore = new ArrayList<>();
    }

    public void addMedia(Media media){
        if (itemsInStore.contains(media)){
            System.out.println("Media is already added");
        }
        else{
            itemsInStore.add(media);
            System.out.println("Media " + media.getTitle() + " added");
        }
    }

    public void removeMedia(Media media){
        if (itemsInStore.contains(media)){
            itemsInStore.remove(media);
            System.out.println("Media " + media.getTitle() + " removed");
        }
        else{
            System.out.println("Media " + media.getTitle() + " is not in the store");
        }
    }

    public Media search (String title){
        for (Media media : itemsInStore){
            if (media.getTitle().equals(title)){
                return media;
            }
        }
        return null;
    }
}