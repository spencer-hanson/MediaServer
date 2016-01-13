package net.matrixstudios.playlistgenerator;

import net.matrixstudios.playlistgenerator.generator.Generator;
import net.matrixstudios.playlistgenerator.generator.SongNotFoundException;
import net.matrixstudios.playlistgenerator.generator.filefinder.FilenameFilter;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSongFile;
import net.matrixstudios.playlistgenerator.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistGenerator implements ActionListener {

    private Window window;

    public PlaylistGenerator() {
        FilenameFilter.createRegexFile();
        window = new Window(this);
        window.display();
    }


    /** Attempts to create the playlist, and returns an array of the output */
    private ArrayList<PlaylistSongFile> runList(String source, String folder) {

        ArrayList<PlaylistSongFile> returnVal = null;

        try {
            System.out.println("Running List \'" + source + "\' in Folder \'" + folder + "\'");
            System.out.flush();
            returnVal = new Generator(source, folder).getGenerateList();
            Generator.printList(returnVal);
            System.out.println("Done Running List!");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch(SongNotFoundException e) {
            System.err.println("Song not found: \'" + e.getMissingSong().getSongName() + "\' by \'" + e.getMissingSong().getBandName() + "\'");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if((returnVal != null)) { System.out.println("Running List Failed!"); }

            return returnVal;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand().toLowerCase();
        if(cmd.equals("generate")) {

            String testSource = "/mnt/SEAGATE/test.txt";
            String testFolder = "/mnt/SEAGATE/Music";

            runList(testSource, testFolder);
        } else if(cmd.startsWith("playlist")) {
            //open file finder here
        } else if (cmd.startsWith("search folder")) {
            //open file finder here
        }
    }

    private JComponent findComponentByActionCmd(String actionCmd) {
        JTextField jt = null;
        for(JComponent component : window.getDataComponents()) {
            //if(component.get)
        }
        return null;
    }
}
