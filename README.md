# Student Grade Tracker

A Java application for managing and tracking student grades with a graphical user interface.

## Features

- **Student Management**: Add and remove students from the system
- **Grade Management**: Add grades (0-100) to individual students
- **Statistics Calculation**: 
  - Calculate average, highest, and lowest scores for each student
  - Calculate overall statistics across all students
- **Summary Reports**: Generate comprehensive reports showing all student data and statistics
- **User-Friendly GUI**: Intuitive Swing-based interface with organized panels

## Requirements

- Java Development Kit (JDK) 8 or higher
- No external dependencies required (uses standard Java libraries)

## Project Structure

```
Student Grade Tracker/
├── Student.java          # Student class with grade management
├── GradeTracker.java     # Main tracker class with statistics
├── GradeTrackerGUI.java  # GUI interface (Swing)
├── Main.java            # Application entry point
└── README.md            # This file
```

## How to Compile

1. Open a terminal/command prompt in the project directory
2. Compile all Java files:
   ```bash
   javac *.java
   ```

## How to Run

After compiling, run the application:
```bash
java Main
```

Or directly:
```bash
java GradeTrackerGUI
```

## Usage Guide

### Adding a Student
1. Enter the student's name in the "Name" field (left panel)
2. Click "Add Student" button
3. The student will appear in the student list

### Adding Grades
1. Select a student from the list
2. Enter a grade (0-100) in the "Grade" field (center panel)
3. Click "Add Grade to Selected Student"
4. The student's statistics will update automatically

### Viewing Student Information
- Select a student from the list to view their:
  - All grades
  - Average score
  - Highest score
  - Lowest score
  - Total number of grades

### Generating Reports
1. Click "Generate Report" button (right panel)
2. A comprehensive summary report will be displayed showing:
   - All students and their grades
   - Individual statistics for each student
   - Overall statistics across all students

### Removing a Student
1. Select a student from the list
2. Click "Remove Selected" button
3. Confirm the removal when prompted

## Data Storage

The application uses `ArrayList` collections to store:
- List of students
- Grades for each student

All data is stored in memory and will be lost when the application is closed.

## Example Workflow

1. Add students: "Alice", "Bob", "Charlie"
2. Add grades for Alice: 85, 90, 88
3. Add grades for Bob: 92, 87, 95
4. Add grades for Charlie: 78, 82, 80
5. Generate report to see all statistics
6. View individual student info by selecting them

## Technical Details

- **Student Class**: Manages individual student data and calculates per-student statistics
- **GradeTracker Class**: Manages the collection of students and calculates overall statistics
- **GradeTrackerGUI Class**: Provides the Swing-based graphical interface
- **Main Class**: Entry point that launches the GUI application

## Future Enhancements

Potential improvements:
- Save/load data to/from files
- Export reports to text files
- Grade categories (homework, exams, projects)
- Weighted grade calculations
- Data persistence with database
- Console-based alternative interface
