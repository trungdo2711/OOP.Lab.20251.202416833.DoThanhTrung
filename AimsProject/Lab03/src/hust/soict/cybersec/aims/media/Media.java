package hust.soict.cybersec.aims.media;

import java.util.Objects;
import java.util.Comparator;
import java.util.Collections;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nbMedia = 0;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media(String title) {
        this.title = title;
        this.id = ++nbMedia;
    }
    public Media(String title, String category) {
        this.title = title;
        this.category = category;
        this.id = ++nbMedia;
    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = ++nbMedia;
    }

    public int getID() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
    public float getCost() {
        return cost;
    }
    public String toString(){
        return "Media - Title: [" + title +"] - Category: [" + category +"] - Cost: [" + cost +"] $";
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Media)){
            return false;
        }
        Media that = (Media)object;

        return Objects.equals(this.getTitle(), that.getTitle());
    }

    public void play() {
        System.out.println("Playing Media");
    }
}
