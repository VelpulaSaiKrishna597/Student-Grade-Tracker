import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of students and provides statistics calculations.
 */
public class GradeTracker {
    private List<Student> students;
    
    /**
     * Creates a new GradeTracker with an empty student list.
     */
    public GradeTracker() {
        this.students = new ArrayList<>();
    }
    
    /**
     * Adds a new student to the tracker.
     * 
     * @param name The student's name
     * @return The created Student object
     */
    public Student addStudent(String name) {
        Student student = new Student(name);
        students.add(student);
        return student;
    }
    
    /**
     * Removes a student from the tracker.
     * 
     * @param student The student to remove
     * @return true if the student was removed, false otherwise
     */
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }
    
    /**
     * Gets all students in the tracker.
     * 
     * @return A list of all students
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Finds a student by name.
     * 
     * @param name The name to search for
     * @return The student if found, null otherwise
     */
    public Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Calculates the overall average grade across all students.
     * 
     * @return The overall average, or 0 if no students exist
     */
    public double getOverallAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        int count = 0;
        for (Student student : students) {
            if (student.getGradeCount() > 0) {
                sum += student.getAverage();
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }
    
    /**
     * Gets the highest grade across all students.
     * 
     * @return The highest grade, or 0 if no grades exist
     */
    public double getOverallHighest() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double highest = Double.MIN_VALUE;
        for (Student student : students) {
            if (student.getGradeCount() > 0) {
                double studentHighest = student.getHighest();
                if (studentHighest > highest) {
                    highest = studentHighest;
                }
            }
        }
        return highest == Double.MIN_VALUE ? 0.0 : highest;
    }
    
    /**
     * Gets the lowest grade across all students.
     * 
     * @return The lowest grade, or 0 if no grades exist
     */
    public double getOverallLowest() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double lowest = Double.MAX_VALUE;
        for (Student student : students) {
            if (student.getGradeCount() > 0) {
                double studentLowest = student.getLowest();
                if (studentLowest < lowest) {
                    lowest = studentLowest;
                }
            }
        }
        return lowest == Double.MAX_VALUE ? 0.0 : lowest;
    }
    
    /**
     * Gets the total number of students.
     * 
     * @return The number of students
     */
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * Generates a summary report of all students.
     * 
     * @return A formatted string containing the summary
     */
    public String generateSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("STUDENT GRADE TRACKER - SUMMARY REPORT\n");
        report.append("=".repeat(60)).append("\n\n");
        
        if (students.isEmpty()) {
            report.append("No students in the system.\n");
        } else {
            report.append(String.format("Total Students: %d\n\n", students.size()));
            
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                report.append(String.format("Student %d: %s\n", i + 1, student.getName()));
                report.append(String.format("  Grades: %s\n", formatGrades(student.getGrades())));
                report.append(String.format("  Average: %.2f\n", student.getAverage()));
                report.append(String.format("  Highest: %.2f\n", student.getHighest()));
                report.append(String.format("  Lowest: %.2f\n", student.getLowest()));
                report.append("\n");
            }
            
            report.append("-".repeat(60)).append("\n");
            report.append("OVERALL STATISTICS:\n");
            report.append(String.format("Overall Average: %.2f\n", getOverallAverage()));
            report.append(String.format("Overall Highest: %.2f\n", getOverallHighest()));
            report.append(String.format("Overall Lowest: %.2f\n", getOverallLowest()));
        }
        
        report.append("=".repeat(60)).append("\n");
        return report.toString();
    }
    
    /**
     * Formats a list of grades as a string.
     * 
     * @param grades The list of grades
     * @return A formatted string of grades
     */
    private String formatGrades(List<Double> grades) {
        if (grades.isEmpty()) {
            return "No grades";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grades.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.format("%.2f", grades.get(i)));
        }
        return sb.toString();
    }
}

