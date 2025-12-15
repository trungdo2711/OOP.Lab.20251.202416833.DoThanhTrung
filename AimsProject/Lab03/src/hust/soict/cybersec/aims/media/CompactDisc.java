package hust.soict.cybersec.aims.media;

import hust.soict.cybersec.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            for (Track track : tracks) {
                track.play();
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
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

    @Override
    public String playGUI() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Playing CD: ").append(this.getTitle()).append(" (Total Length: ").append(this.getLength()).append("s)\n");

        for (Track track : tracks) {
            try {
                sb.append("  -> ").append(track.playGUI()).append("\n");
            } catch (PlayerException e) {
                throw e;
            }
        }
        return sb.toString();
    }

    public List<Track> getTracks() {
        return tracks;
    }
}