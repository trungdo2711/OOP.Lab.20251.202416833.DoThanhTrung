package hust.soict.cybersec.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public Book(String title) {
        super(title);
    }
    public Book(String title, String category) {
        super(title, category);
    }
    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public void addAuthor(String authorName){
        if(!authors.contains(authorName)){
            authors.add(authorName);
        }
        else{
            System.out.println("Author already exists");
        }
    }
    public void removeAuthor(String authorName){
        if(authors.contains(authorName)){
            authors.remove(authorName);
        }
        else{
            System.out.println("Author does not exists");
        }
    }
    public List<String> getAuthors(){
        return authors;
    }
    @Override
    public String toString(){
        String titleStr = (getTitle() != null ? getTitle() : "No title");
        String authorStr = (authors.isEmpty() ? "No authors" : String.join(", ", authors));
        String categoryStr = (getCategory() != null ? getCategory() : "No category");
        String costStr = (getCost() > 0 ? String.valueOf(getCost()) : "No cost");

        return "Book - Title: [" + titleStr +"] - Category: [" + categoryStr +"] - Authors: [" + authorStr +"] - Cost: [" + costStr +"] $";
    }
}
