package com.technohacks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        
        frame.getContentPane().setBackground(Color.BLACK);
        
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 25));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);

        operator = "";
        num1 = num2 = result = 0;

        
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(Color.GRAY);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 16));
            numberButtons[i].setPreferredSize(new Dimension(60,60));
        }

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton decButton = new JButton(".");
        JButton equButton = new JButton("=");
        JButton delButton = new JButton("DEL");
        JButton clrButton = new JButton("CLR");

        JButton[] functionButtons = {addButton, subButton, mulButton, divButton, decButton, equButton};
        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setBackground(Color.GRAY);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 16));
        }

        delButton.setBackground(Color.ORANGE);
        delButton.setForeground(Color.WHITE);

        clrButton.setBackground(Color.ORANGE);
        clrButton.setForeground(Color.WHITE);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.BLACK);

        
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(delButton, BorderLayout.EAST);
        frame.add(clrButton, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            textField.setText(textField.getText() + command);
        } else if (command.equals("CLR")) {
            textField.setText("");
        } else if (command.equals("DEL")) {
                        String temp = textField.getText();
            textField.setText(temp.length() > 0 ? temp.substring(0, temp.length() - 1) : "");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        } else {
            if (!textField.getText().isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }
}
