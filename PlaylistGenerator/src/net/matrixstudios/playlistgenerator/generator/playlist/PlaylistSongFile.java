package net.matrixstudios.playlistgenerator.generator.playlist;

import java.io.File;

/**
 * Created by Spencer Hanson on 1/10/2016.
 */
public class PlaylistSongFile {

    private PlaylistSong song;
    private File file;


    public PlaylistSong getSong() {
        return song;
    }

    public File getFile() {
        return file;
    }

    public void setSong(PlaylistSong song) {
        this.song = song;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public PlaylistSongFile(String band, String track, File file) {
        this.song = new PlaylistSong(band, track);
        this.file = file;
    }

    public String toString() {
        return song.toString() + ":\'" + file.getAbsolutePath() + "\'";
    }
}
