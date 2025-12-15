package hust.soict.cybersec.aims.media;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {
    public static void main(String[] args) {
        Track track1 = new Track("Track One", 180);
        Track track2 = new Track("Track Two", 240);

        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);

        List<Media> mediaList = new ArrayList<Media>();

        CompactDisc cd = new CompactDisc("Album Name", "Music", "Artist Y", 15.00f, 0, "Dir. X");

        Book book = new Book("Classic Novel", "Literature", 20.00f);
        book.addAuthor("Famous Author");

        DigitalVideoDisc2 dvd = new DigitalVideoDisc2( "Action Movie", "Action", "A. Director", 110, 25.50f);

        mediaList.add(cd);
        mediaList.add(book);
        mediaList.add(dvd);

        for(Media m: mediaList){
            System.out.println(m.toString());
        }
    }
}