// File: App.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        // Create backend object
        TextProcessor processor = new TextProcessor();

        // Create a JFrame (main window)
        JFrame frame = new JFrame("Frontend + Backend Demo");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI components
        JTextField inputField = new JTextField(20);
        JButton processButton = new JButton("Process");
        JLabel resultLabel = new JLabel("Result will appear here...");

        // Layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter text: "));
        panel.add(inputField);
        panel.add(processButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        // Button action
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                String result = processor.reverseText(userInput); // Backend call
                resultLabel.setText("Result: " + result);
            }
        });
    }
}
