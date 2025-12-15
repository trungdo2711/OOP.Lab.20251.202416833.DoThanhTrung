package hust.soict.cybersec.aims.media;

import hust.soict.cybersec.aims.exception.PlayerException;

import java.util.Objects;

public class Track implements Playable{
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }
    public void play(){
        System.out.println("Playing Track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }
    public int getLength() {
        return length;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Track)){
            return false;
        }
        Track track = (Track) object;

        return Objects.equals(this.title, track.title) && this.length == track.length;
    }
    @Override
    public String playGUI() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }

        return "Playing Track: " + this.getTitle() + " (Length: " + this.getLength() + "s)";
    }

}