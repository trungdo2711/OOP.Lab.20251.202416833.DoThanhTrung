package hust.soict.cybersec.aims.store.Store;

import hust.soict.cybersec.aims.media.DigitalVideoDisc2;
import hust.soict.cybersec.aims.media.Media;

import java.util.ArrayList;
import java.util.List;

public class Store{
    private ArrayList<Media> itemsInStore;

    public Store(){
        this.itemsInStore = new ArrayList<>();
    }

    public void addMedia(Media media) {
        boolean isExist = false;
        for (Media item : itemsInStore) {
            if (item.getTitle().equalsIgnoreCase(media.getTitle())) {
                isExist = true;
                break;
            }
        }

        if (isExist) {
            System.out.println("Media is already added");
        } else {
            itemsInStore.add(media);
            System.out.println("Media " + media.getTitle() + " added");
        }
    }

    public void removeMedia(Media media) {
        Media mediaToRemove = null;

        for (Media item : itemsInStore) {
            if (item.getTitle().equalsIgnoreCase(media.getTitle())) {
                mediaToRemove = item;
                break;
            }
        }

        if (mediaToRemove != null) {
            itemsInStore.remove(mediaToRemove);
            System.out.println("Media " + media.getTitle() + " removed");
        } else {
            System.out.println("Media " + media.getTitle() + " is not in the store");
        }
    }

    public Media search (String title){
        for (Media media : itemsInStore){
            if (media.getTitle().equalsIgnoreCase(title)){
                return media;
            }
        }
        return null;
    }

    public List<Media> getItemsInStore() {
        List<Media> itemsToReturn = new ArrayList<>();
        itemsToReturn.addAll(itemsInStore);
        return itemsToReturn;
    }
}