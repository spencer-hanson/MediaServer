package net.matrixstudios.playlistgenerator;

import net.matrixstudios.playlistgenerator.generator.Generator;
import net.matrixstudios.playlistgenerator.generator.SongNotFoundException;
import net.matrixstudios.playlistgenerator.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class PlaylistGenerator implements ActionListener {

    private Window window;

    public PlaylistGenerator() {
        window = new Window(this);
        window.display();
    }


    /** Tests if the list has any errors or not, True - good list. False - bad list */
    private boolean runList(String source, String folder) {

        boolean returnVal = true;

        try {
            System.out.println("Testing List \'" + source + "\' in Folder \'" + folder + "\'");
            System.out.flush();
            Generator.printList(new Generator(source, folder).getGenerateList());
            System.out.println("Done Testing List!");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            returnVal = false;
        } catch(SongNotFoundException e) {
            System.err.println("Song not found: \'" + e.getMissingSong().getSongName() + "\' by \'" + e.getMissingSong().getBandName() + "\'");
            returnVal = false;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            returnVal = false;
        } finally {
            if(!returnVal) { System.out.println("Testing List Failed!"); }
            return returnVal;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand().toLowerCase();
        if(cmd.equals("generate")) {

            String testSource = "D:\\test.txt";
            String testFolder = "D:\\Music";

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
