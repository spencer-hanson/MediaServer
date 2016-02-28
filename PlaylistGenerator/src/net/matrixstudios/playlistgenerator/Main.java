package net.matrixstudios.playlistgenerator;

import net.matrixstudios.musicfileapi.MusicFileAPI;
import net.matrixstudios.playlistgenerator.generator.Generator;
import net.matrixstudios.playlistgenerator.generator.filefinder.FilenameFilter;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistReader;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSongFile;

import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class Main {

    public static void usage() {
        System.out.println("Incorrect usage!");
        System.out.println("PlaylistGenerator [-f] <source folder> <playlist file> <output file>");
        System.out.println("-f Will print output to file and supress gui");
        System.exit(0);
    }

    public static void main(String[] args) {

        if(args.length > 0) {
            if(args.length < 4 || args.length > 4) {
                usage();
            }
            try {
                if(args[0].equals("-f")) {
                    String folder = args[1];
                    String source = args[2];
                    String output = args[3];

                    ArrayList<PlaylistSongFile> returnVal = null;
                    Generator generator = new Generator(source, folder);
                    returnVal = new Generator(source, folder).getGenerateList();
                    writePlaylistToFile(returnVal, output);
                }

            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        } else {
            new PlaylistGenerator();
        }


    }

    public static void writePlaylistToFile(ArrayList<PlaylistSongFile> playlist, String filename) throws Exception {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        String tmp = "";

        for(PlaylistSongFile songFile : playlist) {
            tmp = songFile.getFile().toString();
            writer.println(tmp);
        }
        writer.close();
    }


}
