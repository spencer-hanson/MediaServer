package net.matrixstudios.playlistgenerator.generator.filefinder;

import java.io.File;
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

    public FileFinder(String strDir) throws IOException {
        File dir = new File(strDir);
        File[] files = dir.listFiles();
        names = new HashMap<String, File>();
        for(File file : files) {
            names.put(file.getName(), file);
        }
    }

    public File getMatch(String search) {
        File file = null;
        int smallestHam = Integer.MAX_VALUE;
        Iterator it = names.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, File> pair = (Map.Entry<String, File>)it.next(); //TODO? Sort by hamming size? (Smallest to largest)
            int newHam = getHammingDist(search, pair.getKey());
            if(newHam < smallestHam) {
                smallestHam = newHam;
                file = pair.getValue();
            }

        }


        return file;
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
