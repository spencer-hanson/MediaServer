package net.matrixstudios.playlistgenerator.generator.filefinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Spencer Hanson on 1/10/16.
 */
public class FilenameFilter {

    private static String[] trackNumRegexes;

    public static String removeExtension(String filename) {
        String[] filenameArray = filename.split("\\.");
        return filenameArray[0];
    }

    private static boolean checkPattern(String regex, String filename) {
        return Pattern.compile(regex).matcher(filename).matches();
    }

    public static void createRegexFile() {
        ArrayList<String> regexes = readRegexFile();
        trackNumRegexes = new String[regexes.size()];
        int i = 0;
        for(String s : regexes) {
            trackNumRegexes[i] = s;
            i++;
        }
    }

    private static ArrayList<String> readRegexFile() {
        ArrayList<String> regexes = new ArrayList<String>();

        Path file = Paths.get("regex.txt");
        try {
            InputStream in = Files.newInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                regexes.add(line);
            }

        } catch(NoSuchFileException e) {
            createDefaultRegexFile();
            return readRegexFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regexes;
    }

    private static void createDefaultRegexFile() {
        String fileName = "regex.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("(\\d{1,})(\\s?)(\\-?)(\\s?)\n");
            bufferedWriter.write("(.*)(\\s{1,})(\\-)(\\s{1,})(.*)(\\s{1,})(\\-)(\\s{1,})(\\d{1,})(\\s{1,})(\\-)(\\s{1,})\n");
            bufferedWriter.write("(\\w{1,})(\\_)(\\w{1,})(\\_)(\\d{1,})(\\_)\n");
            bufferedWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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
                //regex = "\\b" + regex + "\\b";
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
