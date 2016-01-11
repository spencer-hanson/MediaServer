package net.matrixstudios.playlistgenerator.gui;

import net.matrixstudios.playlistgenerator.PlaylistGenerator;
import net.matrixstudios.playlistgenerator.generator.Generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Spencer Hanson on 1/9/2016.
 */
public class Window extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;

    private PlaylistGenerator plGen;
    private ArrayList<JComponent> dataComponents;

    public void initComponents() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel entirePanel = new JPanel();
        entirePanel.setLayout(new BoxLayout(entirePanel, BoxLayout.Y_AXIS));

        entirePanel.add(createChooserPanel("Playlist"));
        entirePanel.add(createChooserPanel("Search Folder"));
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT/2));

        JButton generateBtn = createButton("Generate");


        add(entirePanel);
        add(spacePanel);

        add(generateBtn);
    }


    private JPanel createContainerPanel() {
        JPanel panel = new JPanel();//new GridLayout(0, 1));
        return panel;
    }
    private JPanel createTextField(String name) {
        JTextField jt = new JTextField();
        jt.setActionCommand(name + ":textfield");
        jt.setEditable(false);
        jt.setBackground(Color.white);
        jt.setPreferredSize(new Dimension(WIDTH/2, 20));
        dataComponents.add(jt);
        JPanel panel = createContainerPanel();
        panel.add(jt);
        return panel;
    }

    private JPanel createChooserButton(String name) {
        JButton button = createButton("Browse..");
        button.setActionCommand(name + ":button");
        dataComponents.add(button);
        JPanel panel = createContainerPanel();
        panel.add(button);
        return panel;
    }

    private JPanel createChooserPanel(String nameToFind) {
        JPanel panel = new JPanel();
        panel.setBorder(
                BorderFactory.createTitledBorder("Find " + nameToFind + "..."));
        panel.setOpaque(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(createTextField(nameToFind));
        panel.add(createChooserButton(nameToFind));
        return panel;
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

        //pack();

    }

    public Window(PlaylistGenerator plGen) {
        super("Playlist Generator");
        this.plGen = plGen;
        this.dataComponents = new ArrayList<JComponent>();
        init();

    }

    public ArrayList<JComponent> getDataComponents() {
        return dataComponents;
    }

    public void display() {
        setVisible(true);
    }
}
