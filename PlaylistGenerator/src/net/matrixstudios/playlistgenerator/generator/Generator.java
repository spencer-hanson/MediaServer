package net.matrixstudios.playlistgenerator.generator;

import net.matrixstudios.playlistgenerator.generator.filefinder.FileFinder;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistReader;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSong;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSongFile;

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
            throw e;
        } catch(IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<PlaylistSong> readPlaylist() throws IOException {
       return plReader.readPlaylist();
    }

    public ArrayList<PlaylistSongFile> getGenerateList() throws IOException, SongNotFoundException, Exception {
        ArrayList<PlaylistSongFile> songs = new ArrayList<PlaylistSongFile>();

        ArrayList<PlaylistSong> songNames = readPlaylist();

        FileFinder fileFinder = new FileFinder(new File(searchDir), false);
        for(PlaylistSong songName : songNames) {
            try {
                File found1 = fileFinder.getMatch(songName.getBandName(), false);
                FileFinder songFinder = new FileFinder(found1, true);
                File found2 = songFinder.getMatch(songName.getSongName(), true);
                songs.add(new PlaylistSongFile(songName.getSongName(), songName.getBandName(), found2));
            } catch(FileNotFoundException e) {
                throw new SongNotFoundException("Song not found:" + songName.toString(), songName);
            }
            //System.out.println("Band name: \'" + songName.getBandName() + "\' match with: \'" + found1.getName() + "\' file: \'" + found1.getAbsolutePath()+ "\'");
            //System.out.println("Song name: \'" + songName.getSongName() + "\' match with: \'" + found2.getName() + "\' file: \'" + found2.getAbsolutePath() + "\'");
        }

        return songs;
    }

    public static void printList(ArrayList<PlaylistSongFile> songFiles) {
        int i = 0;
        System.out.println("Printing Song File List...");
        for(PlaylistSongFile song : songFiles) {
            System.out.println(i++ + ". " + song.toString());
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
