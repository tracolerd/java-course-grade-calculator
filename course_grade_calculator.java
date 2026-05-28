import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Java GUI application to calculate a course grade based on user-provided
 * marks and weightage for assignments, midterms, and finals.
 */
public class CourseGradeCalculator extends JFrame {

    // GUI components
    private final JTextField assignmentMarksField;
    private final JTextField assignmentWeightField;
    private final JTextField midtermMarksField;
    private final JTextField midtermWeightField;
    private final JTextField finalMarksField;
    private final JTextField finalWeightField;
    private final JButton calculateButton;
    private final JTextArea resultArea;

    /**
     * Constructor to set up the GUI frame and its components.
     */
    public CourseGradeCalculator() {
        // Set up the main frame
        setTitle("Course Grade Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Use GridBagLayout for a more structured and professional layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // -- Input Fields and Labels --
        
        // Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(new JLabel("<html><b style='font-size:14px;'>Course Grade Calculator</b></html>"), gbc);
        
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        mainPanel.add(new JLabel("Category"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("Marks (%)"), gbc);
        gbc.gridx = 2;
        mainPanel.add(new JLabel("Weightage (%)"), gbc);
        
        // Assignments row
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Assignments"), gbc);
        gbc.gridx = 1;
        assignmentMarksField = new JTextField(5);
        mainPanel.add(assignmentMarksField, gbc);
        gbc.gridx = 2;
        assignmentWeightField = new JTextField(5);
        mainPanel.add(assignmentWeightField, gbc);

        // Midterms row
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Midterms"), gbc);
        gbc.gridx = 1;
        midtermMarksField = new JTextField(5);
        mainPanel.add(midtermMarksField, gbc);
        gbc.gridx = 2;
        midtermWeightField = new JTextField(5);
        mainPanel.add(midtermWeightField, gbc);

        // Finals row
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(new JLabel("Finals"), gbc);
        gbc.gridx = 1;
        finalMarksField = new JTextField(5);
        mainPanel.add(finalMarksField, gbc);
        gbc.gridx = 2;
        finalWeightField = new JTextField(5);
        mainPanel.add(finalWeightField, gbc);

        // -- Calculation Button --
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        calculateButton = new JButton("Calculate Grade");
        mainPanel.add(calculateButton, gbc);
        
        // -- Result Area --
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        resultArea = new JTextArea(8, 20);
        resultArea.setEditable(false);
        resultArea.setMargin(new Insets(5, 5, 5, 5));
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        mainPanel.add(new JScrollPane(resultArea), gbc);

        add(mainPanel);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
    }

    /**
     * Calculates the final grade based on the input values.
     */
    private void calculateGrade() {
        try {
            // Get marks and weights from text fields
            double assignmentMarks = Double.parseDouble(assignmentMarksField.getText());
            double assignmentWeight = Double.parseDouble(assignmentWeightField.getText());
            double midtermMarks = Double.parseDouble(midtermMarksField.getText());
            double midtermWeight = Double.parseDouble(midtermWeightField.getText());
            double finalMarks = Double.parseDouble(finalMarksField.getText());
            double finalWeight = Double.parseDouble(finalWeightField.getText());

            // Validate weights
            if (assignmentWeight + midtermWeight + finalWeight != 100) {
                resultArea.setText("Error: Total weightage must be 100%.");
                return;
            }

            // Calculate weighted average
            double totalPercentage = 
                (assignmentMarks * (assignmentWeight / 100)) +
                (midtermMarks * (midtermWeight / 100)) +
                (finalMarks * (finalWeight / 100));

            // Determine grade and grade point
            String grade = getLetterGrade(totalPercentage);
            double gradePoint = getGradePoint(totalPercentage);

            // Display results
            resultArea.setText(
                "Calculation Results:\n" +
                "--------------------\n" +
                String.format("Total Percentage: %.2f%%\n", totalPercentage) +
                String.format("Letter Grade: %s\n", grade) +
                String.format("Grade Point: %.2f\n", gradePoint)
            );

        } catch (NumberFormatException ex) {
            resultArea.setText("Error: Please enter valid numbers in all fields.");
        }
    }

    /**
     * Determines the letter grade based on the total percentage.
     * @param percentage The total calculated percentage.
     * @return The corresponding letter grade as a String.
     */
    private String getLetterGrade(double percentage) {
        if (percentage >= 80) return "A+";
        if (percentage >= 75) return "A";
        if (percentage >= 70) return "A-";
        if (percentage >= 65) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 55) return "B-";
        if (percentage >= 50) return "C+";
        if (percentage >= 45) return "C";
        if (percentage >= 40) return "C-";
        return "F";
    }

    /**
     * Determines the grade point based on the total percentage.
     * @param percentage The total calculated percentage.
     * @return The corresponding grade point as a double.
     */
    private double getGradePoint(double percentage) {
        if (percentage >= 80) return 4.00;
        if (percentage >= 75) return 3.75;
        if (percentage >= 70) return 3.50;
        if (percentage >= 65) return 3.25;
        if (percentage >= 60) return 3.00;
        if (percentage >= 55) return 2.75;
        if (percentage >= 50) return 2.50;
        if (percentage >= 45) return 2.25;
        if (percentage >= 40) return 2.00;
        return 0.00;
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseGradeCalculator().setVisible(true);
        });
    }
}