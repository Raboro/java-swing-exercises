package io.github.raboro.library;

import javax.swing.*;
import java.awt.*;

public class Library extends JFrame {

    private final JPanel mainPanel;
    private JButton submitBookButton;
    private final JTextField nameField = new JTextField();
    private final JTextField authorField = new JTextField();
    private final JTextField releaseDateField = new JTextField();

    Library() {
        super("Library");
        setLookAndFeel();
        mainPanel = new JPanel(new FlowLayout());
        this.add(constructMain());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            System.out.println("Error: " + e);
        }
    }

    private Component constructMain() {
        mainPanel.add(constructAddBook());
        return mainPanel;
    }

    private Component constructAddBook() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(constructGridPanel());
        submitBookButton = new JButton("Submit Book");
        submitBookButton.setPreferredSize(new Dimension(300, 20));
        panel.add(submitBookButton);
        return panel;
    }

    private Component constructGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(3, 2));
        gridPanel.add(new JLabel("Name"));
        gridPanel.add(nameField);
        gridPanel.add(new JLabel("Author"));
        gridPanel.add(authorField);
        gridPanel.add(new JLabel("Release Date"));
        gridPanel.add(releaseDateField);
        return gridPanel;
    }
}
