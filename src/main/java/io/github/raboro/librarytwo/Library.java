package io.github.raboro.librarytwo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library extends JFrame {

    private static final String FILE_PATH = "src\\main\\java\\io\\github\\raboro\\librarytwo\\library.txt";
    private final List<Book> books = new ArrayList<>();
    private final JTextField title = new JTextField("Title");
    private final JTextField author = new JTextField("Author");
    private final JTextField year = new JTextField("Year");
    private final JTextField publisher = new JTextField("Publisher");
    private final JButton submit = new JButton("Save entry");
    private final JButton titleOrder = new JButton("Title");
    private final JButton authorOrder = new JButton("Author");
    private final JButton yearOrder = new JButton("Year");
    private final JButton publisherOrder = new JButton("Publisher");

    public static void main(String[] args) {
        new Library();
    }

    Library() {
        super("Library");
        fetchBooksFromFile();
        setSize(500, 200);
        add(constructMainPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void fetchBooksFromFile() {
        readFile()
                .stream()
                .map(this::parseLine)
                .forEach(books::add);
    }

    private List<String> readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Book parseLine(String line) {
        String[] split = line.split(",");
        return new Book(split[0], split[1], Integer.parseInt(split[2]), split[3]);
    }

    private Component constructMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(constructInput());
        panel.add(constructSubmit());
        panel.add(constructOutput());
        addActionListener();
        return panel;
    }

    private Component constructInput() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 2, 3));
        panel.add(new JLabel("Title"));
        panel.add(title);
        title.setPreferredSize(new Dimension(100, 10));
        panel.add(new JLabel("Author"));
        panel.add(author);
        author.setPreferredSize(new Dimension(100, 10));
        panel.add(new JLabel("Year"));
        panel.add(year);
        year.setPreferredSize(new Dimension(100, 10));
        panel.add(new JLabel("Publisher"));
        panel.add(publisher);
        publisher.setPreferredSize(new Dimension(100, 10));
        return panel;
    }

    private Component constructSubmit() {
        JPanel panel = new JPanel();
        panel.add(submit);
        submit.setPreferredSize(new Dimension(490, 20));
        return panel;
    }

    private Component constructOutput() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Ordered output:"));
        panel.add(titleOrder);
        panel.add(authorOrder);
        panel.add(yearOrder);
        panel.add(publisherOrder);
        return panel;
    }

    private void addActionListener() {
        submit.addActionListener(e -> {
            final Book book = new Book(
                title.getText(),
                author.getText(),
                Integer.parseInt(year.getText()),
                publisher.getText()
            );
            books.add(book);
            writeToFile();
        });
        titleOrder.addActionListener(e -> addActionListenerSort(Comparator.comparing(Book::title)));
        authorOrder.addActionListener(e -> addActionListenerSort(Comparator.comparing(Book::author)));
        yearOrder.addActionListener(e -> addActionListenerSort(Comparator.comparing(Book::year)));
        publisherOrder.addActionListener(e -> addActionListenerSort(Comparator.comparing(Book::publisher)));
    }

    private void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            books.forEach(book -> {
                try {
                    writer.write(book.toString());
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addActionListenerSort(Comparator<Book> comparator) {
        books.sort(comparator);
        JOptionPane.showMessageDialog(null, toMessage());
    }

    private String toMessage() {
        return books.stream()
                .map(book -> book.toString() + "\n")
                .collect(Collectors.joining());
    }
}
