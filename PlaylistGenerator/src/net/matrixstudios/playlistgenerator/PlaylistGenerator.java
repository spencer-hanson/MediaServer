package net.matrixstudios.playlistgenerator;

import net.matrixstudios.playlistgenerator.generator.Generator;
import net.matrixstudios.playlistgenerator.generator.SongNotFoundException;
import net.matrixstudios.playlistgenerator.generator.filefinder.FilenameFilter;
import net.matrixstudios.playlistgenerator.generator.playlist.PlaylistSongFile;
import net.matrixstudios.playlistgenerator.gui.TextField;
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

            System.out.println("Done Running List!");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch(SongNotFoundException e) {
            System.err.println("Song not found: \'" + e.getMissingSong().getSongName() + "\' by \'" + e.getMissingSong().getBandName() + "\'");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if((returnVal == null)) { System.out.println("Running List Failed!"); }

            return returnVal;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand().toLowerCase();
        if(cmd.equals("generate")) {

            //String testSource = "/mnt/SEAGATE/playlists/M.txt";
            //String testFolder = "/mnt/SEAGATE/Music";

            String source = getTextFieldData("playlist");
            String folder = getTextFieldData("search folder");

            if(source.equals("") || folder.equals("")) {
                System.out.println("No source or search folder!");
                return;
            }


            System.out.println("Playlist: " + getTextFieldData("playlist"));
            System.out.println("Folder: " + getTextFieldData("search folder"));

            ArrayList<PlaylistSongFile> returnList = runList(source, folder);

            window.getOutputText().setText(playlistToString(returnList));

        } else if(cmd.startsWith("playlist")) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(window.getContentPane());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                updateTextField("playlist", fc.getSelectedFile().getAbsolutePath());
            }
        } else if (cmd.startsWith("search folder")) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(window.getContentPane());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                updateTextField("search folder", fc.getSelectedFile().getAbsolutePath());
            }
        }
    }


    public String playlistToString(ArrayList<PlaylistSongFile> playlist) {
        String output = "";
        for(PlaylistSongFile songFile : playlist) {
            output = output + songFile.getFile().getAbsolutePath() + "\n";
        }

        return output;
    }

    private String getTextFieldData(String name) {
        for(TextField component : window.getDataComponents()) {
            if(component.getName().startsWith(name)) {
                return component.getText();
            }
        }
        return "";
    }

    private void updateTextField(String name, String text) {
        for(TextField component : window.getDataComponents()) {
            if(component.getName().startsWith(name)) {
                component.setText(text);
                window.repaint();
                break;
            }
        }
    }
}
