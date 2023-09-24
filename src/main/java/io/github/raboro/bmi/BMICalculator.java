package io.github.raboro.bmi;

import javax.swing.*;
import java.awt.*;

public class BMICalculator extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final JButton calculateButton = new JButton("Calculate");
    private final JLabel wightText = new JLabel("Weight [kg]");
    private final JTextField weightInput = new JTextField();
    private final JLabel heightText = new JLabel("Height [m]");
    private final JTextField heightInput = new JTextField();
    private final JRadioButton maleButton = new JRadioButton("Male");
    private final JRadioButton femaleButton = new JRadioButton("Female");
    private final JTextField bmiResultText = new JTextField();
    private final JTextField weightResultText = new JTextField();

    public static void main(String[] args) {
        new BMICalculator();
    }

    BMICalculator() {
        super("BMI Calculator");
        setSize(300, 300);
        add(createMainPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Component createMainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(constructWeight());
        mainPanel.add(constructHeight());
        mainPanel.add(constructGender());
        JPanel calcPanel = new JPanel();
        calcPanel.add(calculateButton);
        calculateButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(calcPanel);
        mainPanel.add(constructResultDisplay());
        calculateButton.addActionListener(e -> {
            final float height = (float) Math.pow(Float.parseFloat(heightInput.getText()), 2);
            final float bmi = Float.parseFloat(weightInput.getText()) / height;
            bmiResultText.setText(String.valueOf(bmi));
            if ((bmi < 20 && maleButton.isSelected()) || (bmi < 19 && femaleButton.isSelected())) {
                weightResultText.setText("Short weight");
            } else if ((bmi >= 20 && bmi <= 25 && maleButton.isSelected()) || (bmi >= 19 && bmi <= 24 && femaleButton.isSelected())) {
                weightResultText.setText("Normal weight");
            } else if ((bmi >= 25 && bmi <= 30 && maleButton.isSelected()) || (bmi >= 24 && bmi <= 30 && femaleButton.isSelected())) {
                weightResultText.setText("Overweight");
            } else if ((bmi >= 30 && bmi <= 40) && maleButton.isSelected() || femaleButton.isSelected()) {
                weightResultText.setText("Adiposity");
            } else {
                weightResultText.setText("Massive Adiposity");
            }
        });
        return mainPanel;
    }

    private Component constructWeight() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(heightText);
        panel.add(heightInput);
        heightInput.setPreferredSize(new Dimension(100, 20));
        return panel;
    }

    private Component constructHeight() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(wightText);
        panel.add(weightInput);
        weightInput.setPreferredSize(new Dimension(100, 20));
        return panel;
    }

    private Component constructGender() {
        JPanel panel = new JPanel(new FlowLayout());
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleButton);
        bg.add(femaleButton);
        panel.add(maleButton);
        panel.add(femaleButton);
        return panel;
    }

    private Component constructResultDisplay() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel firstPanel = new JPanel(new FlowLayout());
        firstPanel.add(new JLabel("BMI:"));
        firstPanel.add(bmiResultText);
        bmiResultText.setPreferredSize(new Dimension(100, 20));
        panel.add(firstPanel);
        JPanel secondPanel = new JPanel();
        secondPanel.add(weightResultText);
        weightResultText.setPreferredSize(new Dimension(200, 20));
        panel.add(secondPanel);
        return panel;
    }
}
