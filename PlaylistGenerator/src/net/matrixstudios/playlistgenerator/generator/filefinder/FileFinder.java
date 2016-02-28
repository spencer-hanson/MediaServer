package net.matrixstudios.playlistgenerator.generator.filefinder;

import net.matrixstudios.musicfileapi.MusicFileAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class FileFinder {

    private HashMap<String, File> names;
    private boolean fileOnly; //means can only return file from search

    public FileFinder(File dir, boolean fileOnly) throws IOException {
        File[] files = dir.listFiles();
        this.fileOnly = fileOnly;
        names = new HashMap<String, File>();
        for(File file : files) {
            names.put(file.getName(), file);
        }
    }


    private File getMatch(String search, HashMap<String, File> namesDB, boolean isLive) throws FileNotFoundException, Exception {
        File file = null;
        int smallestHam = Integer.MAX_VALUE;
        Iterator it = namesDB.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, File> pair = (Map.Entry<String, File>) it.next(); //TODO? Sort by hamming size? (Smallest to largest)
            File tmpFile = pair.getValue();
            String foundName = pair.getKey();

            if (MusicFileAPI.isValidFile(tmpFile)) {
                MusicFileAPI musicFile = new MusicFileAPI(tmpFile);
                try {

                    foundName = musicFile.getTitle();

                    if(foundName.contains("feat.")) {
                        foundName = foundName.substring(0, foundName.indexOf('(')-1);
                    }

                } catch (NullPointerException e) {
                    foundName = FilenameFilter.filterFilename(pair.getKey(), pair.getValue().isDirectory());
                    e.printStackTrace();
                }
            } else {
                //System.out.println("Found file, but invalid type: " + tmpFile.getName());
            }

            int newHam = 999;
            //path or filename contains the string "live"
            if (pair.getKey().toLowerCase().contains("live") || pair.getValue().getAbsolutePath().toLowerCase().contains("live")) {
                //song is verified live (tm)
                if(isLive) {
                    //we're good, find song
                    newHam = getHammingDist(search.toLowerCase(), foundName.toLowerCase());
                }
            } else {
                if(!isLive) {
                    newHam = getHammingDist(search.toLowerCase(), foundName.toLowerCase());
                }
            }

                if (newHam < smallestHam) { //smaller, better match
                    smallestHam = newHam;
                    file = pair.getValue();
                }

        }

        if(smallestHam >= search.length()/4) { //match is too small, not found
            throw new FileNotFoundException();
        }

        return file;
    }


    public File getMatch(String search, boolean recursive, boolean isLive) throws FileNotFoundException, Exception {
        if(!recursive) {
            return getMatch(search, names, isLive);
        } else {
            HashMap<String, File> names_tmp = new HashMap<String, File>();


            for(Map.Entry<String, File> pair : names.entrySet()) {

                if(pair.getValue().isDirectory()) {
                    File dir = pair.getValue();
                    File[] files = dir.listFiles();
                    for(File file : files) {
                        if(fileOnly) {
                            if(!file.isDirectory()) {
                                names_tmp.put(file.getName(), file);
                            }
                        } else {
                            names_tmp.put(file.getName(), file);
                        }

                    }
                }
            }
            File match = getMatch(search, names_tmp, isLive);
            return match;
        }
    }

    private int getHammingDist(String str1, String str2) {
        int hamLen = 0;
        if(str2.length() < str1.length()) { return getHammingDist(str2, str1); }
        for(int i = 0;i<str1.length();i++) {
            if(str1.charAt(i) != str2.charAt(i)) {
                hamLen++;
            }
        }
        hamLen += str2.length() - str1.length();
        return hamLen;
    }
}
