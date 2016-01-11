package net.matrixstudios.playlistgenerator.generator.filefinder;

import java.util.regex.Matcher;
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

    private static final String[] trackNumRegexes = {
            "(\\d{1,})(\\s?)(\\-?)(\\s?)", //example: 01 - Song.mp3
            "(.*)(\\s{1,})(\\-)(\\s{1,})(.*)(\\s{1,})(\\-)(\\s{1,})(\\d{1,})(\\s{1,})(\\-)(\\s{1,})" //example: Band - Album - 01 - Song.mp3

    };

    public static boolean hasTrackNumInfront(String filename) {
        boolean result = false;
        for(String regex : trackNumRegexes) {
            if(checkPattern(regex + "(.*)", filename)) {
                //System.out.println("Matches regex: \'" + regex + "\'");
                result = true;
            }

        }

        return result;
    }


    public static void testString(String str) {
        boolean testTrack = FilenameFilter.hasTrackNumInfront(str);
        System.out.println("Testing Original string: \'" + str + "\'");
        System.out.println("Matches pattern: " +testTrack);
        System.out.println("Removing extension: \'" + FilenameFilter.removeExtension(str) + "\'");
        if(testTrack) {
            System.out.println("Removing track num: \'" + FilenameFilter.removeTrackNum(str) + "\'");
        }
        System.out.println("Full func: \'" + FilenameFilter.filterFilename(str, false) + "\'");
        System.out.println("---");
     }


    public static String removeTrackNum(String filename) {
        int end = -1;
        for(String regex : trackNumRegexes) {
            if(checkPattern(regex + "(.*)", filename)) {
                regex = "\\b" + regex + "\\b";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(filename);
                if(m.find()) {
                    end = m.end();
                }
            }
        }
        filename = filename.substring(end);
        return filename;
    }

    public static String filterFilename(String filename, boolean isDirectory) {
        if(isDirectory) { return filename; }

        if(hasTrackNumInfront(filename)) {
            filename = removeTrackNum(filename);
        }

        filename = removeExtension(filename);

        return filename;
    }
}
