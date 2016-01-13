package net.matrixstudios.playlistgenerator;

import net.matrixstudios.musicfileapi.MusicFileAPI;
import net.matrixstudios.playlistgenerator.generator.filefinder.FilenameFilter;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistReader;

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

    public static void main(String[] args) {
       new PlaylistGenerator();
    }



}
