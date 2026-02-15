package algorithms;

import models.Course;
import java.util.*;

public class GraphAlgorithms {
    
    // Dijkstra's Algorithm for finding shortest course prerequisite path
    public static List<String> findShortestPrerequisitePath(
            Map<String, Course> courses, 
            String startCourse, 
            String targetCourse) {
        
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(
            Comparator.comparingInt(distances::get)
        );
        
        // Initialize
        for (String courseCode : courses.keySet()) {
            distances.put(courseCode, Integer.MAX_VALUE);
            previous.put(courseCode, null);
        }
        distances.put(startCourse, 0);
        queue.add(startCourse);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            if (current.equals(targetCourse)) {
                break;
            }
            
            Course currentCourse = courses.get(current);
            if (currentCourse == null) continue;
            
            for (String prerequisite : currentCourse.getPrerequisites()) {
                int newDist = distances.get(current) + 1;
                
                if (newDist < distances.get(prerequisite)) {
                    distances.put(prerequisite, newDist);
                    previous.put(prerequisite, current);
                    queue.remove(prerequisite);
                    queue.add(prerequisite);
                }
            }
        }
        
        // Reconstruct path
        List<String> path = new ArrayList<>();
        String current = targetCourse;
        
        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        
        return path.size() > 1 || path.get(0).equals(startCourse) ? path : new ArrayList<>();
    }
    
    // DFS for course prerequisite traversal
    public static List<String> dfsPrerequisiteTraversal(
            Map<String, Course> courses, 
            String startCourse) {
        
        List<String> traversal = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        
        dfsHelper(courses, startCourse, visited, traversal);
        
        return traversal;
    }
    
    private static void dfsHelper(
            Map<String, Course> courses, 
            String courseCode, 
            Set<String> visited, 
            List<String> traversal) {
        
        if (visited.contains(courseCode)) {
            return;
        }
        
        visited.add(courseCode);
        traversal.add(courseCode);
        
        Course course = courses.get(courseCode);
        if (course != null) {
            for (String prerequisite : course.getPrerequisites()) {
                dfsHelper(courses, prerequisite, visited, traversal);
            }
        }
    }
    
    // Check for circular prerequisites (cycle detection)
    public static boolean hasCircularPrerequisites(Map<String, Course> courses) {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();
        
        for (String courseCode : courses.keySet()) {
            if (hasCycle(courses, courseCode, visited, recursionStack)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean hasCycle(
            Map<String, Course> courses, 
            String courseCode, 
            Set<String> visited, 
            Set<String> recursionStack) {
        
        if (recursionStack.contains(courseCode)) {
            return true;
        }
        
        if (visited.contains(courseCode)) {
            return false;
        }
        
        visited.add(courseCode);
        recursionStack.add(courseCode);
        
        Course course = courses.get(courseCode);
        if (course != null) {
            for (String prerequisite : course.getPrerequisites()) {
                if (hasCycle(courses, prerequisite, visited, recursionStack)) {
                    return true;
                }
            }
        }
        
        recursionStack.remove(courseCode);
        return false;
    }
}
