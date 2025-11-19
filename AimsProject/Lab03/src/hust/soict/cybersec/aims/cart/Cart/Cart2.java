package hust.soict.cybersec.aims.cart.Cart;

import hust.soict.cybersec.aims.disc.DigitalVideoDisc.DigitalVideoDisc2;

import java.util.ArrayList;

public class Cart2 {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<DigitalVideoDisc2> itemsOrdered = new ArrayList<>();

    public void addDigitalVideoDisc(DigitalVideoDisc2 disc) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(disc);
            System.out.println("The disc \"" + disc.getTitle() + "\" has been added.");
        } else {
            System.out.println("The cart is full.");
        }
    }

    public void addDigitalVideoDiscByArrayList(ArrayList<DigitalVideoDisc2> dvdList) {
        int availableSlots = MAX_NUMBERS_ORDERED - itemsOrdered.size();
        if (availableSlots <= 0) {
            System.out.println("The cart is full.");
            return;
        } else {
            if (dvdList.size() <= availableSlots) {
                itemsOrdered.addAll(dvdList);
                System.out.println("Added " + dvdList.size() + " items to the cart.");
            } else {
                for (int i = 0; i < availableSlots; i++) {
                    itemsOrdered.add(dvdList.get(i));
                }
                System.out.println("Added " + availableSlots + " DVDs. The cart is full.");
            }
        }
    }

    public void addDigitalVideoDiscByVariable(DigitalVideoDisc2... discs) {
        int availableSlots = MAX_NUMBERS_ORDERED - itemsOrdered.size();
        if (availableSlots <= 0) {
            System.out.println("The cart is full.");
            return;
        } else {
            if (discs.length <= availableSlots) {
                for (int i = 0; i < discs.length; i++) {
                    itemsOrdered.add(discs[i]);
                }
                System.out.println("Added " + discs.length + " items to the cart.");
            } else {
                for (int i = 0; i < availableSlots; i++) {
                    itemsOrdered.add(discs[i]);
                }
                System.out.println("Added " + availableSlots + " DVDs. The cart is full.");
            }
        }
    }

    public void addDigitalVideoDiscByTwo(DigitalVideoDisc2 dvd1, DigitalVideoDisc2 dvd2) {
        if (MAX_NUMBERS_ORDERED - itemsOrdered.size() >= 2) {
            itemsOrdered.add(dvd1);
            itemsOrdered.add(dvd2);
        } else if (MAX_NUMBERS_ORDERED - itemsOrdered.size() == 1) {
            itemsOrdered.add(dvd1);
            System.out.println("The cart is full. Can only add " + dvd1.getTitle() + " of two discs");
        } else {
            System.out.println("The cart is full. Cannot add anymore");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc2 disc) {
        boolean removed = itemsOrdered.remove(disc);
        if (removed) {
            System.out.println("The disc \"" + disc.getTitle() + "\" has been removed.");
        } else {
            System.out.println("The disc \"" + disc.getTitle() + "\" was not found in the cart.");
        }
    }

    public double totalCost() {
        double total = 0;
        for (DigitalVideoDisc2 disc : itemsOrdered) {
            total += disc.getCost();
        }
        return total;
    }

    public void searchForDVDByID(String id){
        if (itemsOrdered.isEmpty()){
            System.out.println("The cart is empty");
        }
        for (int i  = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).getDigitalDiscID().equals(id)){
                System.out.println("The disc with the id " + id + " is " + itemsOrdered.get(i).getTitle());
                break;
            }
        }
        System.out.println("The disc with the id " + id + " is not found in the cart.");
    }

    public void searchForDVDByTitle(String title){
        if (itemsOrdered.isEmpty()){
            System.out.println("The cart is empty");
        }
        for (int i  = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).isMatch(title)){
                System.out.println("The disc with the title " + title + " is found in the cart.");
                break;
            }
        }
        System.out.println("The disc with the title " + title + " is not found in the cart.");
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
}