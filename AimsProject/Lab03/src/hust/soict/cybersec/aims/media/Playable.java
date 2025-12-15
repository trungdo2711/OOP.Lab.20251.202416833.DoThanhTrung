package hust.soict.cybersec.aims.media;

import hust.soict.cybersec.aims.exception.PlayerException;

public interface Playable{
    public void play() throws PlayerException;

    public String playGUI() throws PlayerException;
}
