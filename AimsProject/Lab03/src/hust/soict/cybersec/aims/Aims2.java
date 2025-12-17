package hust.soict.cybersec.aims;

import hust.soict.cybersec.aims.cart.Cart.Cart2;
import hust.soict.cybersec.aims.exception.DuplicateItemException;
import hust.soict.cybersec.aims.exception.PlayerException;
import hust.soict.cybersec.aims.media.*;
import hust.soict.cybersec.aims.store.Store.Store;

import javax.naming.LimitExceededException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aims2 {

    private static Cart2 cart = new Cart2();
    private static Store store = new Store();

    public static void main(String[] args) throws LimitExceededException, DuplicateItemException {

        DigitalVideoDisc2 dvd1 = new DigitalVideoDisc2( "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc2 dvd2 = new DigitalVideoDisc2( "Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc2 dvd3 = new DigitalVideoDisc2("Aladin", "Animation", 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        Book book1 = new Book("The Age of Innocence", "Literature", 12.50f);
        Book book2 = new Book("Snow Crash", "Science Fiction", 9.99f);
        Book book3 = new Book("And Then There Were None", "Mystery", 8.75f);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        CompactDisc cd1 = new CompactDisc("Retro Future", "Synthwave", "Neon Rider", 18.99f, 0, "None");
        Track track1_1 = new Track("Grid Runners", 285);
        Track track1_2 = new Track("A.I. Dreams", 310);
        Track track1_3 = new Track("Pixel Coastline", 240);
        cd1.addTrack(track1_1);
        cd1.addTrack(track1_2);
        cd1.addTrack(track1_3);

        CompactDisc cd2 = new CompactDisc("Echoes in the Canyon", "Folk Rock", "The Wanderer Collective", 12.50f, 0, "None");
        Track track2_1 = new Track("Dusty Road Blues", 205);
        Track track2_2 = new Track("Riverbend Waltz", 190);
        Track track2_3 = new Track("Sunrise Hymn", 350);
        cd2.addTrack(track2_1);
        cd2.addTrack(track2_2);
        cd2.addTrack(track2_3);
        store.addMedia(cd1);
        store.addMedia(cd2);

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while(!exit){
            showMenu();

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0-3).");
                scanner.nextLine();
                continue;
            }

            switch(option){
                case 0:
                    exit = true;
                    System.out.println("Thank you for using this program.");
                    break;
                case 1:
                    storeMenu(scanner);
                    break;
                case 2:
                    updateStore(scanner);
                    break;
                case 3:
                    cartMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu(Scanner scanner) throws LimitExceededException, DuplicateItemException {
        boolean back = false;
        while(!back){
            System.out.println("\n--- STORE ITEMS ---");
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media’s details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0-4).");
                scanner.nextLine();
                continue;
            }

            switch(option){
                case 0:
                    back = true;
                    break;
                case 1:
                    System.out.println("Please enter the title of the media you would like to see:");
                    String titleToSee = scanner.nextLine();
                    Media mediaTitle = store.search(titleToSee);
                    if (mediaTitle != null){
                        System.out.println("\nDetails: ");
                        System.out.println(mediaTitle);
                        mediaDetailsMenu(scanner, mediaTitle);
                    }
                    else{
                        System.out.println("Media not found in the store.");
                    }
                    break;
                case 2:
                    System.out.println("Please enter the title of the media to add:");
                    String titleToAdd = scanner.nextLine();
                    Media mediaToAdd = store.search(titleToAdd);
                    if (mediaToAdd != null){
                        cart.addMedia(mediaToAdd);
                    }
                    else{
                        System.out.println("Media not found in the store.");
                    }
                    break;
                case 3:
                    System.out.println("Please enter the title of the media to play:");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = store.search(titleToPlay);
                    if (mediaToPlay != null){
                        if (mediaToPlay instanceof Playable){
                            try {
                                ((Playable) mediaToPlay).play();
                            } catch (PlayerException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        else{
                            System.out.println("Media type (" + mediaToPlay.getClass().getSimpleName() + ") is not supported for playing.");
                        }
                    }
                    else{
                        System.out.println("Media not found in the store.");
                    }
                    break;
                case 4:
                    cartMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void mediaDetailsMenu(Scanner scanner, Media media) throws LimitExceededException, DuplicateItemException {
        boolean back = false;
        while (!back){
            System.out.println("\n--- MEDIA DETAILS MENU ---");
            System.out.println("1. Add to cart");
            System.out.println("2. Play " + (media instanceof Playable ? "(Available)" : "(Not available)"));
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0-2).");
                scanner.nextLine();
                continue;
            }

            switch(option){
                case 0:
                    back = true;
                    break;
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable){
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    else{
                        System.out.println("Media type (" + media.getClass().getSimpleName() + ") is not supported for playing.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void cartMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            cart.printCart();
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0-5).");
                scanner.nextLine();
                continue;
            }

            switch (option) {
                case 0:
                    back = true;
                    break;
                case 1:
                    System.out.println("Filter medias in cart by id or title. Type 1 for id and 2 for title.");
                    int filterChoice;
                    try {
                        filterChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Filter operation cancelled.");
                        scanner.nextLine();
                        break;
                    }

                    if (filterChoice == 1) {
                        System.out.println("Enter the id to filter:");
                        int id;
                        try {
                            id = scanner.nextInt();
                            scanner.nextLine();
                            cart.searchByID(id);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid ID format. Filter cancelled.");
                            scanner.nextLine();
                        }
                    } else if (filterChoice == 2) {
                        System.out.println("Enter the title to filter:");
                        String title = scanner.nextLine();
                        cart.searchByTitle(title);
                    } else {
                        System.out.println("Invalid filter option.");
                    }
                    break;
                case 2:
                    System.out.println("Please enter the criteria to sort. Type 1 for title and 2 for cost");
                    int sortChoice;
                    try {
                        sortChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Sort operation cancelled.");
                        scanner.nextLine();
                        break;
                    }

                    if (sortChoice == 1) {
                        cart.sortMedia("title");
                    } else if (sortChoice == 2) {
                        cart.sortMedia("cost");
                    } else {
                        System.out.println("Invalid sort option.");
                    }
                    break;
                case 3:
                    System.out.println("Please enter the media name to remove (Type 0 to exit).");
                    String titleToRemove = scanner.nextLine();
                    if (!titleToRemove.equalsIgnoreCase("0")) {
                        Media mediaToRemove = cart.searchToRemove(titleToRemove);
                        if (mediaToRemove != null) {
                            cart.removeMedia(mediaToRemove);
                        } else {
                            System.out.println("Media not found in the cart.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Please enter the media name to play (Type 0 to exit).");
                    String titleToPlay = scanner.nextLine();
                    if (!titleToPlay.equalsIgnoreCase("0")) {
                        Media mediaToPlay = cart.searchToRemove(titleToPlay);
                        if (mediaToPlay == null) {
                            System.out.println("Media not in the cart.");
                        } else if (mediaToPlay instanceof Playable) {
                            try {
                                ((Playable) mediaToPlay).play();
                                System.out.println("Played media " + mediaToPlay.getTitle());
                            } catch (PlayerException e) {
                                System.err.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Media type (" + mediaToPlay.getClass().getSimpleName() + ") is not supported for playing.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n**************************************************");
                    System.out.println("ORDER PLACED SUCCESSFULLY!");
                    System.out.println("Your cart has been emptied.");
                    System.out.println("**************************************************\n");
                    cart.empty();
                    back = true;
                default:
                    System.out.println("Invalid option. Please choose a number between 0 and 5.");
            }
        }
    }

    public static void updateStore(Scanner scanner){
        boolean back = false;
        while (!back){
            System.out.println("\n--- UPDATE STORE MENU ---");
            System.out.println("1. Add a media to the store");
            System.out.println("2. Remove a media from the store");
            System.out.println("0. Back");
            System.out.println("-------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int option;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0-2).");
                scanner.nextLine();
                continue;
            }

            switch(option){
                case 0:
                    back = true;
                    break;
                case 1:
                    System.out.println("\n--- ADD MEDIA TO STORE ---");
                    System.out.println("Select media type to add:");
                    System.out.println("1. Book");
                    System.out.println("2. DVD");
                    System.out.println("3. CD");
                    System.out.println("0. Cancel");

                    int typeChoice;
                    try {
                        typeChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Operation cancelled.");
                        scanner.nextLine();
                        break;
                    }

                    if (typeChoice == 0) break;
                    if (typeChoice < 1 || typeChoice > 3) {
                        System.out.println("Invalid media type.");
                        break;
                    }

                    System.out.println("Enter title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter category:");
                    String category = scanner.nextLine();
                    System.out.println("Enter cost:");
                    float cost;
                    try {
                        cost = scanner.nextFloat();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid cost format. Operation cancelled.");
                        scanner.nextLine();
                        break;
                    }

                    if (typeChoice == 1) {
                        Book newBook = new Book(title, category, cost);
                        System.out.println("Enter author name (Type 'done' to finish):");
                        String author;
                        while (!(author = scanner.nextLine()).equalsIgnoreCase("done")) {
                            newBook.addAuthor(author);
                            System.out.println("Enter next author name (Type 'done' to finish):");
                        }
                        store.addMedia(newBook);

                    } else if (typeChoice == 2) {
                        System.out.println("Enter director:");
                        String director = scanner.nextLine();
                        System.out.println("Enter length (minutes):");
                        int length = 0;
                        try {
                            length = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid length. Defaulting to 0.");
                            scanner.nextLine();
                        }
                        DigitalVideoDisc2 newDvd = new DigitalVideoDisc2(title, category, director, length, cost);
                        store.addMedia(newDvd);

                    } else  {
                        System.out.println("Enter artist:");
                        String artist = scanner.nextLine();
                        System.out.println("Enter director:");
                        String director = scanner.nextLine();

                        CompactDisc newCd = new CompactDisc(title, category, artist, cost, 0, director);

                        System.out.println("Add tracks? (yes/no)");
                        String addTrackChoice = scanner.nextLine();
                        if (addTrackChoice.equalsIgnoreCase("yes") || addTrackChoice.equalsIgnoreCase("y")) {
                            while (true) {
                                System.out.println("Enter track title (or 'done' to finish):");
                                String trackTitle = scanner.nextLine();
                                if (trackTitle.equalsIgnoreCase("done")) break;

                                System.out.println("Enter track length (minutes):");
                                int trackLength;
                                try {
                                    trackLength = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid length. Track not added.");
                                    scanner.nextLine();
                                    continue;
                                }
                                Track newTrack = new Track(trackTitle, trackLength);
                                newCd.addTrack(newTrack);
                            }
                        }
                        store.addMedia(newCd);
                    }
                    break;

                case 2:
                    System.out.println("\n--- REMOVE MEDIA FROM STORE ---");
                    System.out.println("Enter the title of the media to remove:");
                    String titleToRemove = scanner.nextLine();

                    Media mediaToRemove = store.search(titleToRemove);
                    if (mediaToRemove != null) {
                        store.removeMedia(mediaToRemove);
                    } else {
                        System.out.println("Media not found in the store.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 0 and 2.");
                    break;
            }
        }
    }
}