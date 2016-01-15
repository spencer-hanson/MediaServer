package net.matrixstudios.playlistgenerator.gui;

import javax.swing.*;

/**
 * Created by Spencer Hanson on 1/14/16.
 */
public class TextField extends JTextField {
    private String name;

    public TextField(String name) {
        super();
        this.name = name;
    }

    public String getName()  {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
