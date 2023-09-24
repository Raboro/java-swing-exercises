package io.github.raboro.library;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library extends JFrame {

    public static final String FILE_PATH = "src\\main\\java\\io\\github\\raboro\\library\\books.txt";
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
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Book input", "Invalid", JOptionPane.ERROR_MESSAGE);
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
        saveToFileButton = new JButton("Save to File");
        panel.add(saveToFileButton);
        loadFromFileButton = new JButton("Load from File");
        panel.add(loadFromFileButton);
        addActionListenerToButtons();
        return panel;
    }

    private void addActionListenerToButtons() {
        saveToFileButton.addActionListener(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                books.forEach(book -> {
                    try {
                        writer.write(book.toString());
                        writer.newLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loadFromFileButton.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                books.clear();
                reader.lines().forEach(this::parseLine);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void parseLine(String line) {
        String[] split = line.split(" - ");
        books.add(new Book(split[0], split[1], split[2]));
    }
}
