import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GUI interface for the Student Grade Tracker application.
 */
public class GradeTrackerGUI extends JFrame {
    private GradeTracker gradeTracker;
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;
    private JTextField nameField;
    private JTextField gradeField;
    private JTextArea reportArea;
    private JLabel statsLabel;
    
    /**
     * Creates and initializes the GUI.
     */
    public GradeTrackerGUI() {
        gradeTracker = new GradeTracker();
        initializeGUI();
    }
    
    /**
     * Initializes all GUI components.
     */
    private void initializeGUI() {
        setTitle("Student Grade Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Create main panels
        JPanel leftPanel = createLeftPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel rightPanel = createRightPanel();
        
        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        // Update display
        updateStudentList();
        updateStatistics();
    }
    
    /**
     * Creates the left panel with student list and controls.
     */
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Students"));
        panel.setPreferredSize(new Dimension(250, 0));
        
        // Student list
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane listScrollPane = new JScrollPane(studentList);
        listScrollPane.setPreferredSize(new Dimension(240, 400));
        
        // Add student section
        JPanel addStudentPanel = new JPanel(new BorderLayout(5, 5));
        nameField = new JTextField();
        nameField.setToolTipText("Enter student name");
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        
        addStudentPanel.add(new JLabel("Name:"), BorderLayout.NORTH);
        addStudentPanel.add(nameField, BorderLayout.CENTER);
        addStudentPanel.add(addButton, BorderLayout.SOUTH);
        
        // Remove student button
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(e -> removeSelectedStudent());
        
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(addStudentPanel, BorderLayout.NORTH);
        panel.add(removeButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Creates the center panel with grade management.
     */
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Grade Management"));
        
        // Grade input section
        JPanel gradeInputPanel = new JPanel(new BorderLayout(5, 5));
        gradeField = new JTextField();
        gradeField.setToolTipText("Enter grade (0-100)");
        JButton addGradeButton = new JButton("Add Grade to Selected Student");
        addGradeButton.addActionListener(e -> addGrade());
        
        gradeInputPanel.add(new JLabel("Grade (0-100):"), BorderLayout.NORTH);
        gradeInputPanel.add(gradeField, BorderLayout.CENTER);
        gradeInputPanel.add(addGradeButton, BorderLayout.SOUTH);
        
        // Selected student info
        JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Selected Student Info"));
        statsLabel = new JLabel("No student selected");
        statsLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        statsLabel.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane infoScrollPane = new JScrollPane(statsLabel);
        infoScrollPane.setPreferredSize(new Dimension(0, 200));
        
        // Update info when selection changes
        studentList.addListSelectionListener(e -> updateStudentInfo());
        
        panel.add(gradeInputPanel, BorderLayout.NORTH);
        panel.add(infoScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the right panel with report display.
     */
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Summary Report"));
        panel.setPreferredSize(new Dimension(300, 0));
        
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        reportArea.setBackground(Color.WHITE);
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> generateReport());
        
        panel.add(reportScrollPane, BorderLayout.CENTER);
        panel.add(generateReportButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Adds a new student to the tracker.
     */
    private void addStudent() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student name.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (gradeTracker.findStudent(name) != null) {
            JOptionPane.showMessageDialog(this, "A student with this name already exists.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gradeTracker.addStudent(name);
        nameField.setText("");
        updateStudentList();
        updateStatistics();
    }
    
    /**
     * Removes the selected student from the tracker.
     */
    private void removeSelectedStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to remove.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String selectedName = studentListModel.getElementAt(selectedIndex);
        Student student = gradeTracker.findStudent(selectedName);
        
        if (student != null) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to remove " + selectedName + "?", 
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                gradeTracker.removeStudent(student);
                updateStudentList();
                updateStatistics();
                statsLabel.setText("No student selected");
            }
        }
    }
    
    /**
     * Adds a grade to the selected student.
     */
    private void addGrade() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student first.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String gradeText = gradeField.getText().trim();
        if (gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a grade.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double grade = Double.parseDouble(gradeText);
            String selectedName = studentListModel.getElementAt(selectedIndex);
            Student student = gradeTracker.findStudent(selectedName);
            
            if (student != null) {
                student.addGrade(grade);
                gradeField.setText("");
                updateStudentInfo();
                updateStatistics();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Updates the student list display.
     */
    private void updateStudentList() {
        studentListModel.clear();
        for (Student student : gradeTracker.getStudents()) {
            studentListModel.addElement(student.getName());
        }
    }
    
    /**
     * Updates the selected student information display.
     */
    private void updateStudentInfo() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex == -1) {
            statsLabel.setText("No student selected");
            return;
        }
        
        String selectedName = studentListModel.getElementAt(selectedIndex);
        Student student = gradeTracker.findStudent(selectedName);
        
        if (student != null) {
            StringBuilder info = new StringBuilder();
            info.append("Name: ").append(student.getName()).append("\n\n");
            info.append("Grades: ");
            List<Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                info.append("No grades yet");
            } else {
                for (int i = 0; i < grades.size(); i++) {
                    if (i > 0) info.append(", ");
                    info.append(String.format("%.2f", grades.get(i)));
                }
            }
            info.append("\n\n");
            info.append(String.format("Average: %.2f\n", student.getAverage()));
            info.append(String.format("Highest: %.2f\n", student.getHighest()));
            info.append(String.format("Lowest: %.2f\n", student.getLowest()));
            info.append(String.format("Total Grades: %d", student.getGradeCount()));
            
            statsLabel.setText(info.toString());
        }
    }
    
    /**
     * Updates the overall statistics display.
     */
    private void updateStatistics() {
        // Statistics are shown in the report, but we could add a status bar if needed
    }
    
    /**
     * Generates and displays the summary report.
     */
    private void generateReport() {
        String report = gradeTracker.generateSummaryReport();
        reportArea.setText(report);
    }
    
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show GUI
        SwingUtilities.invokeLater(() -> {
            GradeTrackerGUI gui = new GradeTrackerGUI();
            gui.setVisible(true);
        });
    }
}

