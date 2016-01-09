package net.matrixstudios.playlistgenerator.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class Generator {
    private String playlistSource;
    private String searchDir;

    private PlaylistReader plReader;

    public Generator(String playlistSource, String searchDir) throws IOException {
        this.playlistSource = playlistSource;
        this.searchDir = searchDir;

        try {
            plReader = null;
            plReader = new PlaylistReader(playlistSource);
        } catch(FileNotFoundException e) {
            System.err.println("File not found!");
            throw e;
        } catch(IOException e) {
            System.err.println("Error!");
            e.printStackTrace();
            throw e;
        }
    }

    private ArrayList<String> readPlaylist() throws IOException {
       return plReader.readPlaylist();
    }

    public ArrayList<File> getGenerateList() throws IOException {
        ArrayList<File> songFiles = new ArrayList<File>();

        return songFiles;
    }

    public static void printList(ArrayList<File> songFiles) {
        int i = 0;
        for(File file : songFiles) {
            System.out.println(i++ + ". " + file.getAbsoluteFile().toString());
        }
    }



}
