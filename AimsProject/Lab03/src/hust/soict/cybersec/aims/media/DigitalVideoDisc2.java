package hust.soict.cybersec.aims.media;

import hust.soict.cybersec.aims.exception.PlayerException;

public class DigitalVideoDisc2 extends Disc implements Playable{

    public DigitalVideoDisc2(String title) {
        super(title);
    }

    public DigitalVideoDisc2(String title, String category, float cost) {
        super(title, category, cost);
    }
    public DigitalVideoDisc2(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }
    public DigitalVideoDisc2(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD Length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    @Override
    public String toString(){
        String titleStr = (getTitle() != null ? getTitle() : "No title");
        String directorStr = (getDirector() != null ? getDirector() : "No director");
        String categoryStr = (getCategory() != null ? getCategory() : "No category");
        String lengthStr = (getLength() > 0 ? String.valueOf(getLength()) : "No length");
        String costStr = (getCost() > 0 ? String.valueOf(getCost()) : "No cost");

        return "DVD - Title: [" + titleStr +"] - Director: [" + directorStr + "] - Category: [" + categoryStr +"] - Length: [" + lengthStr +"] - Cost: [" + costStr +"] $";
    }
    @Override
    public String playGUI() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }

        return "Playing DVD: " + this.getTitle() +
                " (Director: " + this.getDirector() +
                ", Length: " + this.getLength() + "s)";
    }

}