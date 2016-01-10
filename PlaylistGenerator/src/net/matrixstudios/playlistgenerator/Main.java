package net.matrixstudios.playlistgenerator;

import net.matrixstudios.playlistgenerator.generator.filefinder.FilenameFilter;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class Main {

    public static void main(String[] args) {
        String filename = "10 - Nightmare of a Martyr.flac";
        System.out.println("Original string: \'" + filename + "\'");
        System.out.println("Matches pattern: " + FilenameFilter.hasTrackNumInfront(filename));
        System.out.println("Removing extension: \'" + FilenameFilter.removeExtension(filename) + "\'");
        System.out.println("Removing track num: \'" + FilenameFilter.removeTrackNum(filename) + "\'");
        System.out.println("Full func: \'" + FilenameFilter.filterFilename(filename, false) + "\'");

        //new PlaylistGenerator();
    }
}
