package net.matrixstudios.playlistgenerator.generator.playlist;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistSong {

    private String bandName;
    private String songName;

    public PlaylistSong(String data) {
        String[] dataList = data.split("-");
        songName = dataList[0].trim();
        bandName = dataList[1].trim();
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
