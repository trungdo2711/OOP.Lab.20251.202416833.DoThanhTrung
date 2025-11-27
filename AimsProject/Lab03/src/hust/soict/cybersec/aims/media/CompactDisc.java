package hust.soict.cybersec.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public void play(){
        if(!tracks.isEmpty()){
            System.out.println("Playing CD: " + this.getTitle());
            for(Track track: tracks){
                track.play();
            }
        }
        else{
            System.out.println("No tracks in this album");
        }
        System.out.println("Finished playing CD.");
    }

    public CompactDisc(String title) {
        super(title);
    }
    public CompactDisc(String title, String category, String artist, float cost, int length, String director) {
        super(title, category, director, length, cost);
        this.artist = artist;
    }
    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track){
        if(!tracks.contains(track)){
            tracks.add(track);
        }
        else{
            System.out.println("Track already exists");
        }
    }
    public void removeTrack(Track track){
        if(tracks.contains(track)){
            tracks.remove(track);
        }
        else{
            System.out.println("Track does not exist");
        }
    }
    @Override
    public int getLength(){
        int totalLength = 0;
        for(Track track : tracks){
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public String toString(){
        String titleStr = (getTitle() != null ? getTitle() : "No title");
        String categoryStr = (getCategory() != null ? getCategory() : "No category");
        String directorStr = (getDirector() != null ? getDirector() : "No director");
        String artistStr = (getArtist() != null ? getArtist() : "No artist");
        String lengthStr = (getLength() > 0 ? String.valueOf(getLength()) : "No length");
        String costStr = (getCost() > 0 ? String.valueOf(getCost()) : "No cost");

        return "CD - Title: [" + titleStr +"] - Category: [" + categoryStr +"] - Artist: [" + artistStr + "] - Director: [" + directorStr + "] - Length: [" + lengthStr +"] - Cost: [" + costStr +"] $";
    }
}