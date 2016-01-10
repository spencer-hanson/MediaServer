package net.matrixstudios.playlistgenerator;

import net.matrixstudios.playlistgenerator.generator.Generator;
import net.matrixstudios.playlistgenerator.gui.Window;

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
    private boolean testList() {
        String testSource = "C:\\Users\\M4trix\\Desktop\\test\\test.txt";
        String testFolder = "D:\\Music";
        boolean returnVal = true;

        try {
            System.out.println("Testing List \'" + testSource + "\'\nAnd Folder \'" + testFolder + "\'");
            System.out.flush();
            Generator.printList(new Generator(testSource, testFolder).getGenerateList());
            //Generator.printPlaylist(new Generator(testSource, testFolder).readPlaylist());
            System.out.println("Done Testing List");
        } catch(FileNotFoundException e) {
            returnVal = false;
        }catch (Exception e) {
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
            testList();
        }
    }
}
