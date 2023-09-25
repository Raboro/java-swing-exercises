package io.github.raboro.shell;

import javax.swing.*;
import java.awt.*;

public class Shell extends JFrame {

    private final JTextField nameInput = new JTextField();
    private final JButton shellOneButton = new JButton("Shell 1");
    private final JButton shellTwoButton = new JButton("Shell 2");
    private final JButton shellThreeButton = new JButton("Shell 3");
    private final JButton statsButton = new JButton("Statistics");
    private final JButton exitButton = new JButton("Exit");
    private final JTextField result = new JTextField("");
    private int position;
    private boolean correct = false;

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Shell();
    }

    Shell() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("Shell");
        setSize(300, 300);
        add(constructMainPanel());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        position = getPosition();
    }

    private Component constructMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(constructName());
        mainPanel.add(constructShell());
        mainPanel.add(constructStats());
        mainPanel.add(constructResult());
        addActionListener();
        return mainPanel;
    }

    private Component constructName() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Player Name"));
        panel.add(nameInput);
        nameInput.setPreferredSize(new Dimension(200, 20));
        return panel;
    }

    private Component constructShell() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(shellOneButton);
        panel.add(shellTwoButton);
        panel.add(shellThreeButton);
        return panel;
    }

    private Component constructStats() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(statsButton);
        panel.add(exitButton);
        return panel;
    }

    private Component constructResult() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(result);
        result.setPreferredSize(new Dimension(250, 20));
        return panel;
    }

    private void addActionListener() {
        shellOneButton.addActionListener(e -> evaluate(0));
        shellTwoButton.addActionListener(e -> evaluate(1));
        shellThreeButton.addActionListener(e -> evaluate(2));
        statsButton.addActionListener(e -> result.setText(String.valueOf(correct)));
        exitButton.addActionListener(e -> this.dispose());
    }

    private void evaluate(int value) {
        correct = value == position;
        position = getPosition();
    }

    private int getPosition() {
        return (int) (Math.random() * 2 + 1);
    }
}
