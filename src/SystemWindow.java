import javax.swing.*;
import java.awt.*;

public class SystemWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SystemWindow::createUI);
    }

    private static void createUI() {
        // Main window
        JFrame frame = new JFrame("Neighborhood Recommender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main vertical panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Dropdown options 1–5
        Integer[] options = {1, 2, 3, 4, 5};

        // Question 1
        JPanel q1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q1Label = new JLabel("1. How important is access to public transport (1-5)?");
        JComboBox<Integer> q1Combo = new JComboBox<>(options);
        q1Panel.add(q1Label);
        q1Panel.add(q1Combo);

        // Question 2
        JPanel q2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q2Label = new JLabel("2. How important is neighborhood safety (1-5)?");
        JComboBox<Integer> q2Combo = new JComboBox<>(options);
        q2Panel.add(q2Label);
        q2Panel.add(q2Combo);

        // Question 3
        JPanel q3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q3Label = new JLabel("3. Rank how lively you want your neighborhood (1-5)");
        JComboBox<Integer> q3Combo = new JComboBox<>(options);
        q3Panel.add(q3Label);
        q3Panel.add(q3Combo);

        // Question 4
        JPanel q4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q4Label = new JLabel("4. How important is being close to nature (1-5)?");
        JComboBox<Integer> q4Combo = new JComboBox<>(options);
        q4Panel.add(q4Label);
        q4Panel.add(q4Combo);

        // Question 5
        JPanel q5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q5Label = new JLabel("5. How important is nightlife (1-5)?");
        JComboBox<Integer> q5Combo = new JComboBox<>(options);
        q5Panel.add(q5Label);
        q5Panel.add(q5Combo);

        // OK button (only here we show the final popup)
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        buttonPanel.add(okButton);

        okButton.addActionListener(e -> {
            int transport = (int) q1Combo.getSelectedItem();
            int safety = (int) q2Combo.getSelectedItem();
            int liveliness = (int) q3Combo.getSelectedItem();
            int nature = (int) q4Combo.getSelectedItem();
            int nightlife = (int) q5Combo.getSelectedItem();

            // Placeholder: here you would calculate the best neighborhood
            JOptionPane.showMessageDialog(
                    frame,
                    "(Here we would calculate the best neighborhood.)",
                    "Your Preferences",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Add everything to main panel
        mainPanel.add(q1Panel);
        mainPanel.add(q2Panel);
        mainPanel.add(q3Panel);
        mainPanel.add(q4Panel);
        mainPanel.add(q5Panel);
        mainPanel.add(buttonPanel);

        // Add to frame
        frame.setContentPane(mainPanel);
        frame.pack();              // size window to fit components
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}
