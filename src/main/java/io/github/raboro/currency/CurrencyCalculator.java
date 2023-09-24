package io.github.raboro.currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Optional;
import java.util.function.Consumer;

public class CurrencyCalculator extends JFrame {

    private static final float CONVERT = 2.2F; // random
    private final JPanel mainPanel = new JPanel();
    private final JTextField textField = new JTextField("Convert");
    private final JButton eurToUs = new JButton("Eur -> US");
    private final JButton usToEur = new JButton("US -> Eur");
    private final JButton cancel = new JButton("Cancel");

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new CurrencyCalculator();
    }

    CurrencyCalculator() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("CurrencyCalculator");
        setSize(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(constructMainPanel());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setLocationRelativeTo(null);
    }

    private Component constructMainPanel() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textField, BorderLayout.NORTH);
        mainPanel.add(constructButtonPanel(), BorderLayout.CENTER);
        return mainPanel;
    }

    private Component constructButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(eurToUs);
        panel.add(usToEur);
        panel.add(cancel);
        addActionListener();
        return panel;
    }

    private void addActionListener() {
        eurToUs.addActionListener(e -> getValue().ifPresent(value -> new Converter().accept(String.valueOf(Float.parseFloat(value) * CONVERT))));
        usToEur.addActionListener(e -> getValue().ifPresent(value -> new Converter().accept(String.valueOf(Float.parseFloat(value) / CONVERT))));
        cancel.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

    private Optional<String> getValue() {
        String text = textField.getText();
        return invalidText(text) ? Optional.empty() : Optional.of(text);
    }

    private boolean invalidText(String text) {
        try {
            Float.parseFloat(text);
            return text.isEmpty() || text.isBlank();
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static class Converter implements Consumer<String> {
        @Override
        public void accept(String s) {
            JOptionPane.showMessageDialog(null, s, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
