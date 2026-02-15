package services;

import models.Course;
import algorithms.GraphAlgorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private Map<String, Course> courses;
    
    public CourseService() {
        this.courses = new HashMap<>();
        initializeSampleCourses();
    }
    
    private void initializeSampleCourses() {
        // Add sample courses with prerequisites
        Course dsAlgo = new Course("CS201", "Data Structures & Algorithms", 3, "CS");
        Course oop = new Course("CS101", "Object Oriented Programming", 3, "CS");
        Course database = new Course("CS301", "Database Systems", 3, "CS");
        Course webDev = new Course("CS401", "Web Development", 3, "CS");
        Course network = new Course("CS202", "Computer Networks", 3, "CS");
        
        database.addPrerequisite("CS201");
        database.addPrerequisite("CS101");
        webDev.addPrerequisite("CS301");
        webDev.addPrerequisite("CS101");
        
        courses.put(dsAlgo.getCourseCode(), dsAlgo);
        courses.put(oop.getCourseCode(), oop);
        courses.put(database.getCourseCode(), database);
        courses.put(webDev.getCourseCode(), webDev);
        courses.put(network.getCourseCode(), network);
    }
    
    public boolean addCourse(Course course) {
        if (!courses.containsKey(course.getCourseCode())) {
            courses.put(course.getCourseCode(), course);
            return true;
        }
        return false;
    }
    
    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }
    
    public List<Course> getAllCourses() {
        return new java.util.ArrayList<>(courses.values());
    }
    
    public boolean deleteCourse(String courseCode) {
        return courses.remove(courseCode) != null;
    }
    
    // Find shortest prerequisite path using Dijkstra's algorithm
    public List<String> findPrerequisitePath(String startCourse, String targetCourse) {
        return GraphAlgorithms.findShortestPrerequisitePath(courses, startCourse, targetCourse);
    }
    
    // Get all prerequisites using DFS
    public List<String> getAllPrerequisites(String courseCode) {
        return GraphAlgorithms.dfsPrerequisiteTraversal(courses, courseCode);
    }
    
    // Check for circular prerequisites
    public boolean hasCircularDependencies() {
        return GraphAlgorithms.hasCircularPrerequisites(courses);
    }
    
    public Map<String, Course> getCourseMap() {
        return courses;
    }
}
