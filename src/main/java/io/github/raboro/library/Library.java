package io.github.raboro.library;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Library extends JFrame {

    private final JPanel mainPanel;
    private JButton submitBookButton;
    private final List<Book> books = new ArrayList<>();
    private final JTextField nameField = new JTextField();
    private final JTextField authorField = new JTextField();
    private final JTextField releaseDateField = new JTextField();
    private JButton saveToFileButton;
    private JButton loadFromFileButton;

    Library() {
        super("Library");
        setLookAndFeel();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
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
        mainPanel.add(constructFileInteraction());
        return mainPanel;
    }

    private Component constructAddBook() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(constructGridPanel());
        submitBookButton = new JButton("Submit Book");
        submitBookButton.addActionListener(e -> {
            final String name = nameField.getText();
            final String author = authorField.getText();
            final String releaseDate = releaseDateField.getText();
            if (isValidBook(name, author, releaseDate)) {
                books.add(new Book(name, author, releaseDate));
            }
        });
        panel.add(submitBookButton);
        return panel;
    }

    private boolean isValidBook(String name, String author, String releaseDate) {
        final boolean validName = name != null && !"".equals(name) && !" ".equals(name);
        final boolean validAuthor = author != null && !"".equals(author) && !" ".equals(author);
        final boolean validReleaseDate = releaseDate != null && !"".equals(releaseDate) && !" ".equals(releaseDate);
        return validName && validAuthor && validReleaseDate;
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

    private Component constructFileInteraction() {
        final JPanel panel = new JPanel();
        saveToFileButton =  new JButton("Save to File");
        panel.add(saveToFileButton);
        loadFromFileButton = new JButton("Load from File");
        panel.add(loadFromFileButton);
        return panel;
    }
}
