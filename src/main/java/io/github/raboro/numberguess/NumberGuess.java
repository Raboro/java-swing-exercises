package io.github.raboro.numberguess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberGuess extends JFrame {

    private static final String FILE_PATH  = "src\\main\\java\\io\\github\\raboro\\numberguess\\bestPlayers.txt";
    private int currentNumber;
    private int attempts;
    private boolean correctAnswer;
    private final JTextField nameInput = new JTextField();
    private final JTextField numberInput = new JTextField();
    private final JButton newGameButton = new JButton("New Game");
    private final JButton okButton = new JButton("Ok");
    private final JButton bestPlayerButton = new JButton("Best Player");
    private final JButton exitButton = new JButton("Exit");
    private final JTextField result = new JTextField();


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new NumberGuess();
    }

    NumberGuess() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("NumberGuess");
        setSize(400, 200);
        add(constructMainPanel());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setLocationRelativeTo(null);
        currentNumber = getRandomNumber();
        attempts = 1;
        correctAnswer = false;
    }

    private Component constructMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(constructName());
        mainPanel.add(constructNumberInput());
        mainPanel.add(constructButtons());
        mainPanel.add(constructResult());
        return mainPanel;
    }

    private Component constructName() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Player Name"));
        panel.add(nameInput);
        nameInput.setPreferredSize(new Dimension(100, 20));
        return panel;
    }

    private Component constructNumberInput() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Enter number between 1 and 1000"));
        panel.add(numberInput);
        numberInput.setPreferredSize(new Dimension(100, 20));
        return panel;
    }

    private Component constructButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(newGameButton);
        panel.add(okButton);
        panel.add(bestPlayerButton);
        panel.add(exitButton);
        addActionListener();
        return panel;
    }

    private void addActionListener() {
        newGameButton.addActionListener(e -> {
            saveToFile();
            currentNumber = getRandomNumber();
            attempts = 1;
            result.setText("");
        });
        okButton.addActionListener(e -> {
            final String prefix = "Attempt #".concat(String.valueOf(attempts)).concat(": ");
            int value = Integer.parseInt(numberInput.getText());
            if (value == currentNumber) {
                correctAnswer = true;
                result.setText(prefix.concat(String.valueOf(value)).concat(" Correct"));
                return;
            }
            result.setText(prefix.concat(String.valueOf(value)).concat(value > currentNumber ? " too big!" : " too low!"));
            attempts++;
        });
        bestPlayerButton.addActionListener(e -> {
            List<String> lines = readFromFile();
            Map<String, Integer> pairs = new HashMap<>();
            lines.forEach(line -> {
                String[] split = line.split("-");
                pairs.put(split[0], Integer.valueOf(split[1]));
            });
            pairs.values()
                    .stream()
                    .sorted()
                    .findFirst()
                    .ifPresent(value -> JOptionPane.showMessageDialog(null, value));
        });
        exitButton.addActionListener(e -> this.dispose());
    }

    private void saveToFile() {
        if (!correctAnswer) {
            return;
        }
        java.util.List<String> lines = readFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            lines.add(nameInput.getText().concat("-").concat(String.valueOf(attempts)));
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            // ignored
        }
    }

    private java.util.List<String> readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            // ignored
        }
        return new ArrayList<>();
    }

    private Component constructResult() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(result);
        result.setPreferredSize(new Dimension(370, 20));
        return panel;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 1000 + 1);
    }
}
