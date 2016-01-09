package net.matrixstudios.playlistgenerator.generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistReader {
    private String playlist;

    private FileReader fileReader;
    private BufferedReader bufferedReader;

    private void initReader() throws IOException {
        fileReader = new FileReader(playlist);
        bufferedReader = new BufferedReader(fileReader);
    }

    public PlaylistReader(String playlist) throws IOException {
        this.playlist = playlist;
        initReader();
    }

    public ArrayList<String> readPlaylist() throws IOException {
        ArrayList<String> files = new ArrayList<String>();
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
            files.add(line);
        }
        return files;
    }
}
