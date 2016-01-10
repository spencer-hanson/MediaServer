package net.matrixstudios.playlistgenerator.generator;

import net.matrixstudios.playlistgenerator.generator.filefinder.FileFinder;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistReader;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSong;

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
            plReader = new PlaylistReader(this.playlistSource);
        } catch(FileNotFoundException e) {
            System.err.println("File not found!");
            throw e;
        } catch(IOException e) {
            System.err.println("Error!");
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<PlaylistSong> readPlaylist() throws IOException {
       return plReader.readPlaylist();
    }

    public ArrayList<File> getGenerateList() throws IOException {
        ArrayList<File> songFiles = new ArrayList<File>();
        ArrayList<PlaylistSong> songNames = readPlaylist();

        FileFinder fileFinder = new FileFinder(searchDir);
        for(PlaylistSong songName : songNames) {
            File found1 = fileFinder.getMatch(songName.getBandName(), false);
            FileFinder songFinder = new FileFinder(found1);
            File found2 = songFinder.getMatch(songName.getSongName(), true);
            System.out.println("Band name: \'" + songName.getBandName() + "\' match with: \'" + found1.getName() + "\' file: \'" + found1.getAbsolutePath()+ "\'");
            System.out.println("Song name: \'" + songName.getSongName() + "\' match with: \'" + found2.getName() + "\' file: \'" + found2.getAbsolutePath() + "\'");
        }

        return songFiles;
    }

    public static void printList(ArrayList<File> songFiles) {
        int i = 0;
        System.out.println("Printing Song File List...");
        for(File file : songFiles) {
            System.out.println(i++ + ". " + file.getAbsoluteFile().toString());
        }
        System.out.println("Done Printing Song File List...");
    }

    public static void printPlaylist(ArrayList<PlaylistSong> songFiles) {
        int i = 0;
        System.out.println("Printing Playlist...");
        for(PlaylistSong song : songFiles) {
            System.out.println(i++ + ". " + song.toString());
        }
        System.out.println("Done Printing Playlist");
    }



}
