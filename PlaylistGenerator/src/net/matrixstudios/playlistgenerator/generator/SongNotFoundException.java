package net.matrixstudios.playlistgenerator.generator;

import net.matrixstudios.playlistgenerator.PlaylistGenerator;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSong;

/**
 * Created by Spencer Hanson on 1/10/2016.
 */
public class SongNotFoundException extends Exception {
    PlaylistSong missingSong;

    public SongNotFoundException(String msg, PlaylistSong song) {
        super(msg);
        this.missingSong = song;
    }

    public PlaylistSong getMissingSong() {
        return missingSong;
    }
}
