package net.matrixstudios.playlistgenerator.generator.playlist;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistSong {

    private String bandName;
    private String songName;

    public PlaylistSong(String data) {
        String[] dataList = data.split("(\\s{1})(\\-)(\\s{1})");

        songName = dataList[0].trim();
        bandName = dataList[1].trim();
    }

    public PlaylistSong(String songName, String bandName) {
        this.songName = songName;
        this.bandName = bandName;
    }

    public String getBandName() {
        return bandName;
    }

    public String getSongName() {
        return songName;
    }

    public String toString() {
        return "\'" + getSongName() + "\':\'" + getBandName() + "\'";
    }
}
