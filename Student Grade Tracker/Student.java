import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with their name and grades.
 */
public class Student {
    private String name;
    private List<Double> grades;
    
    /**
     * Creates a new student with the given name.
     * 
     * @param name The student's name
     */
    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }
    
    /**
     * Gets the student's name.
     * 
     * @return The student's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the student's name.
     * 
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Adds a grade to the student's grade list.
     * 
     * @param grade The grade to add (0-100)
     */
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }
    
    /**
     * Gets all grades for this student.
     * 
     * @return A list of grades
     */
    public List<Double> getGrades() {
        return new ArrayList<>(grades);
    }
    
    /**
     * Calculates the average grade for this student.
     * 
     * @return The average grade, or 0 if no grades exist
     */
    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    /**
     * Gets the highest grade for this student.
     * 
     * @return The highest grade, or 0 if no grades exist
     */
    public double getHighest() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }
    
    /**
     * Gets the lowest grade for this student.
     * 
     * @return The lowest grade, or 0 if no grades exist
     */
    public double getLowest() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
    
    /**
     * Gets the total number of grades for this student.
     * 
     * @return The number of grades
     */
    public int getGradeCount() {
        return grades.size();
    }
    
    /**
     * Removes a grade at the specified index.
     * 
     * @param index The index of the grade to remove
     */
    public void removeGrade(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
        }
    }
    
    @Override
    public String toString() {
        return name + " - Average: " + String.format("%.2f", getAverage()) + 
               ", Highest: " + String.format("%.2f", getHighest()) + 
               ", Lowest: " + String.format("%.2f", getLowest());
    }
}

