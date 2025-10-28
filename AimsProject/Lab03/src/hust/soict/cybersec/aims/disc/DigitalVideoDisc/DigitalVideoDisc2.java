package hust.soict.cybersec.aims.disc.DigitalVideoDisc;

public class DigitalVideoDisc2 {
    private static int nbDigitalVideoDisc = 0;

    private static String generateDigitalDiscID(){
        String id = String.format("%08d", nbDigitalVideoDisc);
        nbDigitalVideoDisc++;
        return id;
    }
    private final String id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public String getDigitalDiscID(){return id;}

    public DigitalVideoDisc2(String title) {
        this.id = generateDigitalDiscID();
        this.title = title;
    }

    public DigitalVideoDisc2(String category, String title, float cost) {
        this.id = generateDigitalDiscID();
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public DigitalVideoDisc2(String director, String category, String title, float cost) {
        this.id = generateDigitalDiscID();
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    public DigitalVideoDisc2(String title, String category, String director, int length, float cost) {
        this.id = generateDigitalDiscID();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }
    public String toString(){
        String titleStr = (getTitle() != null ? getTitle() : "No title");
        String directorStr = (getDirector() != null ? getDirector() : "No director");
        String categoryStr = (getCategory() != null ? getCategory() : "No category");
        String lengthStr = (getLength() > 0 ? String.valueOf(getLength()) : "No length");
        String costStr = (getCost() > 0 ? String.valueOf(getCost()) : "No cost");

        return "DVD - [" + titleStr +"] - [" + categoryStr +"] - [" + lengthStr +"] : [" + costStr +"] $";
    }

    public boolean isMatch(String title){
        if (this.getTitle().equals(title)){
            return true;
        }
        return false;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}