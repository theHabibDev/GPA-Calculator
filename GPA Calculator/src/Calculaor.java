import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 class CGPACalculatorGUI {
    private JFrame frame;
    private JTextField[] subjectFields;
    private JTextField[] gpaFields;
    private JTextField[] creditFields;
    private int numSubjects;

     CGPACalculatorGUI(int numSubjects) {
        this.numSubjects = numSubjects;
        subjectFields = new JTextField[numSubjects];
        gpaFields = new JTextField[numSubjects];
        creditFields = new JTextField[numSubjects];

        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("CGPA Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(numSubjects + 1, 3, 10, 5));
        inputPanel.add(new JLabel("Subject Name"));
        inputPanel.add(new JLabel("GPA"));
        inputPanel.add(new JLabel("Credit Hours"));

        for (int i = 0; i < numSubjects; i++) {
            subjectFields[i] = new JTextField();
            gpaFields[i] = new JTextField();
            creditFields[i] = new JTextField();
            inputPanel.add(subjectFields[i]);
            inputPanel.add(gpaFields[i]);
            inputPanel.add(creditFields[i]);
        }

        JButton calculateButton = new JButton("Calculate CGPA");
        JLabel resultLabel = new JLabel("Your CGPA will appear here.");

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double totalPoints = 0;
                int totalCredits = 0;
                try {
                    for (int i = 0; i < numSubjects; i++) {
                        double gpa = Double.parseDouble(gpaFields[i].getText());
                        int credit = Integer.parseInt(creditFields[i].getText());
                        totalPoints += gpa * credit;
                        totalCredits += credit;
                    }
                    double cgpa = totalPoints / totalCredits;
                    resultLabel.setText("Your Total CGPA is: " + String.format("%.2f", cgpa));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric values for GPA and Credit Hours.");
                }
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(calculateButton, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter number of subjects:");
        if (input != null) {
            try {
                int num = Integer.parseInt(input);
                if (num > 0) {
                    new CGPACalculatorGUI(num);
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a positive number.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        }
    }
}
