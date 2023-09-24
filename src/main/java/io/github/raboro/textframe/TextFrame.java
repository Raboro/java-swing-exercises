package io.github.raboro.textframe;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFrame extends JFrame {

    private static final String FILE_PATH = "src\\main\\java\\io\\github\\raboro\\textframe\\file.txt";

    public static void main(String[] args) {
        new TextFrame();
    }

    TextFrame() {
        super("TextFrame");
        add(constructPanel());
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private Component constructPanel() {
        JTextArea area = new JTextArea();
        area.setText(getContentOfFile());
        return area;
    }

    private String getContentOfFile() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.lines().forEach(line -> builder.append(line).append("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

}
