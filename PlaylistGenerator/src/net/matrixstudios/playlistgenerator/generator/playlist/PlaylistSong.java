package net.matrixstudios.playlistgenerator.generator.playlist;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistSong {

    private String bandName;
    private String albumName;
    private String songName;
    private boolean isLive;


    public PlaylistSong(String data) {
        String[] dataList = data.split("(\\s{1})(\\-)(\\s{1})");

        songName = dataList[0].trim();
        bandName = dataList[1].trim();
        if(dataList.length > 2) {
            albumName = dataList[2].trim();
        } else {
            albumName = "*";
        }

        if(songName.startsWith("&")) { //song is live
            isLive = true;
            songName = songName.substring(1); //remove '&' from songname
        } else {
            isLive = false;
        }
    }

    public PlaylistSong(String bandName, String songName, String albumName) {
        this.bandName = bandName;
        this.songName = songName;
        this.albumName = albumName;
    }

    public boolean isLive() {
        return isLive;
    }

    public String getBandName() {
        return bandName;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() { return albumName; }

    public String toString() {
        return "\'" + getSongName() + "\':\'" + getBandName() + "\'";
    }
}
