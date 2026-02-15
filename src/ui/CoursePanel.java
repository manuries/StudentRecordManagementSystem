package ui;

import models.Course;
import services.CourseService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoursePanel extends JPanel {
    
    private CourseService courseService;
    private DefaultTableModel tableModel;
    
    // UI Components
    private JTextField txtCourseCode;
    private JTextField txtCourseName;
    private JTextField txtCredits;
    private JTextField txtDepartment;
    private JTable courseTable;
    private JLabel statusLabel;
    
    public CoursePanel(CourseService courseService) {
        this.courseService = courseService;
        initComponents();
        setupTable();
        refreshTable();
    }
    
    private void setupTable() {
        String[] columns = {"Course Code", "Course Name", "Credits", "Department", "Prerequisites"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        courseTable.setModel(tableModel);
        
        courseTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && courseTable.getSelectedRow() != -1) {
                loadSelectedCourse();
            }
        });
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Course> courses = courseService.getAllCourses();
        
        for (Course course : courses) {
            String prerequisites = String.join(", ", course.getPrerequisites());
            if (prerequisites.isEmpty()) prerequisites = "None";
            
            Object[] row = {
                course.getCourseCode(),
                course.getCourseName(),
                course.getCredits(),
                course.getDepartment(),
                prerequisites
            };
            tableModel.addRow(row);
        }
        
        statusLabel.setText("Total Courses: " + courses.size());
    }
    
    private void loadSelectedCourse() {
        int row = courseTable.getSelectedRow();
        if (row != -1) {
            txtCourseCode.setText(tableModel.getValueAt(row, 0).toString());
            txtCourseName.setText(tableModel.getValueAt(row, 1).toString());
            txtCredits.setText(tableModel.getValueAt(row, 2).toString());
            txtDepartment.setText(tableModel.getValueAt(row, 3).toString());
        }
    }
    
    private void clearFields() {
        txtCourseCode.setText("");
        txtCourseName.setText("");
        txtCredits.setText("");
        txtDepartment.setText("");
        courseTable.clearSelection();
    }
    
    private void addCourse() {
        try {
            String code = txtCourseCode.getText().trim();
            String name = txtCourseName.getText().trim();
            int credits = Integer.parseInt(txtCredits.getText().trim());
            String department = txtDepartment.getText().trim();
            
            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Course Code and Name are required!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (courseService.getCourse(code) != null) {
                JOptionPane.showMessageDialog(this, "Course Code already exists!", 
                    "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Course course = new Course(code, name, credits, department);
            
            if (courseService.addCourse(course)) {
                JOptionPane.showMessageDialog(this, "Course added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTable();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credits must be a number!", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteCourse() {
        int row = courseTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete!", 
                "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String courseCode = tableModel.getValueAt(row, 0).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this course?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (courseService.deleteCourse(courseCode)) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                refreshTable();
            }
        }
    }
    
    private void findPrerequisitePath() {
        String startCourse = JOptionPane.showInputDialog(this, 
            "Enter start course code:", "Find Prerequisite Path", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (startCourse == null || startCourse.trim().isEmpty()) return;
        
        String targetCourse = JOptionPane.showInputDialog(this, 
            "Enter target course code:", "Find Prerequisite Path", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (targetCourse == null || targetCourse.trim().isEmpty()) return;
        
        List<String> path = courseService.findPrerequisitePath(
            startCourse.trim(), targetCourse.trim());
        
        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No path found between these courses!", 
                "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String pathStr = String.join(" → ", path);
            JOptionPane.showMessageDialog(this, 
                "Prerequisite Path:\n" + pathStr, 
                "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void initComponents() {
        // Initialize components
        txtCourseCode = new JTextField();
        txtCourseName = new JTextField();
        txtCredits = new JTextField();
        txtDepartment = new JTextField();
        courseTable = new JTable();
        statusLabel = new JLabel("Total Courses: 0");
        
        JPanel topPanel = new JPanel();
        JLabel lblTitle = new JLabel("Course Management");
        
        JPanel formPanel = new JPanel();
        JLabel lblCourseCode = new JLabel("Course Code:");
        JLabel lblCourseName = new JLabel("Course Name:");
        JLabel lblCredits = new JLabel("Credits:");
        JLabel lblDepartment = new JLabel("Department:");
        
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Course");
        JButton btnDelete = new JButton("Delete Course");
        JButton btnClear = new JButton("Clear Fields");
        JButton btnFindPath = new JButton("Find Prerequisite Path");
        
        JScrollPane scrollPane = new JScrollPane();

        setLayout(new BorderLayout());

        // Top Panel
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblTitle);
        add(topPanel, BorderLayout.NORTH);

        // Form Panel
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(lblCourseCode);
        formPanel.add(txtCourseCode);
        formPanel.add(lblCourseName);
        formPanel.add(txtCourseName);
        formPanel.add(lblCredits);
        formPanel.add(txtCredits);
        formPanel.add(lblDepartment);
        formPanel.add(txtDepartment);

        // Button Panel
        btnAdd.addActionListener(evt -> addCourse());
        btnDelete.addActionListener(evt -> deleteCourse());
        btnClear.addActionListener(evt -> clearFields());
        btnFindPath.addActionListener(evt -> findPrerequisitePath());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnFindPath);

        // Center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Table
        scrollPane.setViewportView(courseTable);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(statusLabel, BorderLayout.SOUTH);
        add(tablePanel, BorderLayout.SOUTH);
        tablePanel.setPreferredSize(new Dimension(1200, 400));
    }
}