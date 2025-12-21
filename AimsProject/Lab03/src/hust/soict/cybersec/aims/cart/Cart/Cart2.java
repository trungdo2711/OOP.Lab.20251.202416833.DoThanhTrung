package hust.soict.cybersec.aims.cart.Cart;

import hust.soict.cybersec.aims.exception.DuplicateItemException;
import hust.soict.cybersec.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.Collections;

import static hust.soict.cybersec.aims.media.Media.COMPARE_BY_COST_TITLE;
import static hust.soict.cybersec.aims.media.Media.COMPARE_BY_TITLE_COST;

public class Cart2 {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) throws LimitExceededException, DuplicateItemException {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException("ERROR: The cart is full (limit " + MAX_NUMBERS_ORDERED + ").");
        }

        for (Media item : itemsOrdered) {
            if (item.getTitle().equalsIgnoreCase(media.getTitle())) {
                throw new DuplicateItemException("ERROR: " + media.getTitle() + " is already in the cart!");
            }
        }

        itemsOrdered.add(media);
        System.out.println("Media " + media.getTitle() + " added");
    }

    public void removeMedia(Media media) {
        Media mediaToRemove = null;
        for (Media item : itemsOrdered) {
            if (item.getTitle().equalsIgnoreCase(media.getTitle())) {
                mediaToRemove = item;
                break;
            }
        }
        if (mediaToRemove != null) {
            itemsOrdered.remove(mediaToRemove);
            System.out.println("Media " + media.getTitle() + " removed");
        } else {
            System.out.println("Media " + media.getTitle() + " not in the cart");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            for (int i = 0; i < itemsOrdered.size(); i++) {
                System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
            }
        }
        System.out.println("Total cost: " + String.format("%.2f", totalCost()) + "$");
        System.out.println("**************************************************");
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found " + media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no media were found with \"" + title + "\" in the title!");
        }
    }

    public Media searchToRemove(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void searchByID(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getID() == id) {
                System.out.println("Found " + media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no media were found that match the ID provided!");
        }
    }

    public void sortMedia(String sortType){
        String sort = sortType.toLowerCase();
        if (sort.equals("title")){
            Collections.sort(itemsOrdered, COMPARE_BY_TITLE_COST);
            System.out.println("Selected sort by title.");
        }
        else if (sort.equals("cost")){
            System.out.println("Selected sort by cost.");
            Collections.sort(itemsOrdered, COMPARE_BY_COST_TITLE);
        }
        else{
            System.out.println("Invalid sort type. Please choose between title and cost.");
        }
    }

    public void empty(){
        itemsOrdered.clear();
    }

    public ObservableList<Media> getItemsOrdered() {
        return FXCollections.observableList(itemsOrdered);
    }
}