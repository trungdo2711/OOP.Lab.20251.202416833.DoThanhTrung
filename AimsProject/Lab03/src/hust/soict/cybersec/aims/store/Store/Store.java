package hust.soict.cybersec.aims.store.Store;

import hust.soict.cybersec.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
    private ObservableList<Media> itemsInStore;

    public Store() {
        this.itemsInStore = FXCollections.observableArrayList();
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
            System.out.println("Media '" + media.getTitle() + "' is already in the store.");
        } else {
            itemsInStore.add(media);
            System.out.println("Media '" + media.getTitle() + "' has been added.");
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
            System.out.println("Media '" + media.getTitle() + "' removed.");
        } else {
            System.out.println("Media '" + media.getTitle() + "' is not in the store.");
        }
    }

    public Media search(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public ObservableList<Media> getItemsInStore() {
        return itemsInStore;
    }
}