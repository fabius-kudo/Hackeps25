import javax.swing.*;
import java.awt.*;

public class SystemWindow {

    public static void startCode() {
        SwingUtilities.invokeLater(SystemWindow::createUI);
    }

    private static void createUI() {
        // Main window
        JFrame frame = new JFrame("Neighborhood Finder (L.A. Edition)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main vertical panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Integer[] options = {1, 2, 3, 4, 5};


        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainTitle = new JLabel("- Ideal Neighborhood Finder -");
        mainTitle.setFont(new Font("Serif", Font.BOLD, 20));
        titlePanel.add(mainTitle);

        // Subtitle panel
        JPanel subtitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel subtitle = new JLabel("Welcome to Ideal Neighborhood Finder! Answer the following questions to find the neighborhood that best fits you.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitlePanel.add(subtitle);

        // Add title + subtitle to main panel
        mainPanel.add(titlePanel);
        mainPanel.add(subtitlePanel);

        // Key
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel infoLabel = new JLabel(
                "<html>" +
                        "<div style='margin-left:20px; font-weight:normal;'>" +
                        "Please consider the following when answering:<br>" +
                        "1 → A very little amount<br>" +
                        "2 → A little amount<br>" +
                        "3 → A moderate amount<br>" +
                        "4 → A great amount<br>" +
                        "5 → A very great amount<br>" +
                        "</div>" +
                        "</html>"
        );

        infoPanel.add(infoLabel);

        mainPanel.add(infoPanel);

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
        JLabel q3Label = new JLabel("3. Rank how lively you want your neighborhood to be (1-5).");
        JComboBox<Integer> q3Combo = new JComboBox<>(options);
        q3Panel.add(q3Label);
        q3Panel.add(q3Combo);

        // Question 4
        JPanel q4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q4Label = new JLabel("4. How important is having access to nature (1-5)?");
        JComboBox<Integer> q4Combo = new JComboBox<>(options);
        q4Panel.add(q4Label);
        q4Panel.add(q4Combo);

        // Question 5
        JPanel q5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel q5Label = new JLabel("5. How much are you willing to pay for extra comfort in your home (1-5)?");
        JComboBox<Integer> q5Combo = new JComboBox<>(options);
        q5Panel.add(q5Label);
        q5Panel.add(q5Combo);

        // Search button
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Search");
        buttonPanel.add(okButton);

        okButton.addActionListener(e -> {
            int transport = (int) q1Combo.getSelectedItem();
            int safety = (int) q2Combo.getSelectedItem();
            int liveliness = (int) q3Combo.getSelectedItem();
            int nature = (int) q4Combo.getSelectedItem();
            int nightlife = (int) q5Combo.getSelectedItem();


            ImageIcon starIcon = new ImageIcon("src/world_star-0.png");
            // Calculates best neighborhood
            JOptionPane.showMessageDialog(
                    frame,
                    "(Here we would calculate the best neighborhood.)",
                    "Your Preferences",
                    JOptionPane.INFORMATION_MESSAGE,
                    starIcon
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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
