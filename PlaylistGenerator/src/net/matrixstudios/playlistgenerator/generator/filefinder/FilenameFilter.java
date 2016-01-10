package net.matrixstudios.playlistgenerator.generator.filefinder;

import java.util.regex.Pattern;

/**
 * Created by m4trixsh4d0w on 1/10/16.
 */
public class FilenameFilter {

    public static String removeExtension(String filename) {
        String[] filenameArray = filename.split("\\.");
        return filenameArray[0];
    }

    private static boolean checkPattern(String regex, String filename) {
        return Pattern.compile(regex).matcher(filename).matches();
    }

    private static final String[] trackNumRegexes = {"(\\d{1,})(\\s?)(\\-)(\\s?)(.*)"};

    public static boolean hasTrackNumInfront(String filename) {
        boolean result = false;
        for(String regex : trackNumRegexes) {
            if(checkPattern(regex, filename)) {
                result = true;
            }

        }

        return result;
    }

    public static String removeTrackNum(String filename) {

        return "";
    }

    public static String filterFilename(String filename, boolean isDirectory) {
        if(isDirectory) { return filename; }
        filename = removeExtension(filename);

        if(hasTrackNumInfront(filename)) {
            filename = removeTrackNum(filename);
        }




        return filename;
    }
}
