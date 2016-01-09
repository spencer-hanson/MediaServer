package net.matrixstudios.playlistgenerator.gui;

import net.matrixstudios.playlistgenerator.PlaylistGenerator;
import net.matrixstudios.playlistgenerator.generator.Generator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class Window extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;

    private PlaylistGenerator plGen;

    public void initComponents() {
        JButton btn = createButton("Generate");
        add(btn);
    }

    private JButton createButton(String name) {
        JButton tmpBtn = new JButton(name);
        tmpBtn.setActionCommand(name.toLowerCase());
        tmpBtn.addActionListener(plGen);
        return tmpBtn;
    }

    public void init() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();

    }

    public Window(PlaylistGenerator plGen) {
        super("Playlist Generator");
        this.plGen = plGen;
        init();

    }

    public void display() {
        setVisible(true);
    }
}
