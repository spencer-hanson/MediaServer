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

    public FileFinder(File dir) throws IOException {
        File[] files = dir.listFiles();
        names = new HashMap<String, File>();
        for(File file : files) {
            names.put(file.getName(), file);
        }
    }

    public FileFinder(String strDir) throws IOException {
        this(new File(strDir));
    }

    private File getMatch(String search, HashMap<String, File> namesDB) throws FileNotFoundException, Exception {
        File file = null;
        int smallestHam = Integer.MAX_VALUE;
        Iterator it = namesDB.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, File> pair = (Map.Entry<String, File>)it.next(); //TODO? Sort by hamming size? (Smallest to largest)
            File tmpFile = pair.getValue();
            String foundName = pair.getKey();
            if(MusicFileAPI.isValidFile(tmpFile)) {
                MusicFileAPI musicFile = new MusicFileAPI(tmpFile);
                try {
                    foundName = musicFile.getTitle();
                } catch(NullPointerException e) {
                    foundName = FilenameFilter.filterFilename(pair.getKey(), pair.getValue().isDirectory());
                    e.printStackTrace();
                }
            }

            int newHam = getHammingDist(search.toLowerCase(), foundName.toLowerCase());
            if(newHam < smallestHam) {
                smallestHam = newHam;
                file = pair.getValue();
            }

        }
        if(smallestHam >= search.length()/4) {
            throw new FileNotFoundException();
        }

        return file;
    }


    public File getMatch(String search, boolean recursive) throws FileNotFoundException, Exception {
        if(!recursive) {
            return getMatch(search, names);
        } else {
            HashMap<String, File> names_tmp = new HashMap<String, File>(names);
            for(Map.Entry<String, File> pair : names.entrySet()) {

                if(pair.getValue().isDirectory()) {
                    File dir = pair.getValue();
                    File[] files = dir.listFiles();
                    for(File file : files) {
                        names_tmp.put(file.getName(), file);
                    }
                }
            }
            File match = getMatch(search, names_tmp);
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
