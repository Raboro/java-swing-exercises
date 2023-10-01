package io.github.raboro.gasstation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GasStations extends JFrame  {

    private final JTextField nameInput = new JTextField();
    private final JTextField dieselInput = new JTextField();
    private final JTextField superE5Input = new JTextField();
    private final JTextField superE10Input = new JTextField();
    private final JButton saveButton = new JButton("Save");
    private final JButton showButton = new JButton("Show");
    private final Map<String, GasPrices> gasStations = new HashMap<>();

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new GasStations();
    }

    GasStations() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("GasStations");
        setSize(300, 200);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        add(constructMainPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Component constructMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(constructInput());
        mainPanel.add(constructButtons());
        return mainPanel;
    }

    private Component constructInput() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 2, 2));
        panel.add(new JLabel("Station Name"));
        panel.add(nameInput);
        panel.add(new JLabel("Diesel"));
        panel.add(dieselInput);
        panel.add(new JLabel("Super E5"));
        panel.add(superE5Input);
        panel.add(new JLabel("Super E10"));
        panel.add(superE10Input);
        return panel;
    }
    private Component constructButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(saveButton);
        panel.add(showButton);
        addActionListener();
        return panel;
    }

    private void addActionListener() {
        saveButton.addActionListener(e -> {
            String name = nameInput.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please provide a station name.", "Name Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double diesel = Double.parseDouble(dieselInput.getText());
            double superE5 = Double.parseDouble(superE5Input.getText());
            double superE10 = Double.parseDouble(superE10Input.getText());
            gasStations.put(name, new GasPrices(diesel, superE5, superE10));
            JOptionPane.showMessageDialog(null, String.format("Saved: %s(%s)", name, gasStations.get(name)));
            nameInput.setText("");
            dieselInput.setText("");
            superE5Input.setText("");
            superE10Input.setText("");
        });
        showButton.addActionListener(e -> JOptionPane.showMessageDialog(null, constructAll()));
    }

    private String constructAll() {
        StringBuilder builder = new StringBuilder();
        gasStations.forEach((key, value) -> builder.append(key.concat(": ").concat(value.toString()).concat("\n")));
        return builder.toString();
    }

}
