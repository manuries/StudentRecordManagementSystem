# Student Record Management System

A comprehensive student record and result management system built using advanced data structures and algorithms for PDSA coursework at NIBM.

##  Project Overview

This system efficiently manages student records, course results, and academic data using Binary Search Trees and Hash Tables, demonstrating practical implementation of data structures and algorithms.

## Key Features

### Student Management
- Add, update, and delete student records
- Search students by ID, name, or department
- Sort students by name (Quick Sort) or GPA (Merge Sort)
- View all students in organized table

### Result Management
- Add course results for students
- Automatic letter grade calculation (A+, A, A-, B+, etc.)
- Real-time weighted GPA computation based on credits
- Display all results per student with grades

###  Course Management
- Manage courses with prerequisite relationships
- Find shortest prerequisite path using Dijkstra's Algorithm
- View all course dependencies
- Pre-loaded sample courses

###  Reports & Analytics
- Overall statistics (total students, average GPA, top performer)
- Top 10 students ranking by GPA
- Department-wise reports and analysis
- Complete detailed student reports with all results

###  Data Persistence
- File-based storage using Java Serialization
- Automatic save on application exit
- Manual save option (Ctrl+S)

##  Data Structures Implemented

### 1. Binary Search Tree (BST)
- **Purpose:** Store and maintain students in sorted order by Student ID
- **Operations:**
  - Insert: O(log n) average case
  - Search: O(log n) average case
  - Delete: O(log n) average case
  - In-order Traversal: O(n)
- **Benefit:** Maintains sorted data efficiently for reports and displays

### 2. Hash Table
- **Purpose:** Fast student lookup by ID
- **Operations:**
  - Insert: O(1) average case
  - Search: O(1) average case
  - Delete: O(1) average case
- **Implementation:** Custom hash function with chaining for collision handling
- **Benefit:** Instant student retrieval for result management

## Algorithms Implemented

| Algorithm | Time Complexity | Usage |
|-----------|----------------|-------|
| **Binary Search** | O(log n) | Search students in sorted list |
| **Merge Sort** | O(n log n) | Sort students by GPA (stable sort) |
| **Quick Sort** | O(n log n) average | Sort students by name |
| **Dijkstra's Algorithm** | O((V+E) log V) | Find shortest prerequisite path |
| **Depth-First Search (DFS)** | O(V + E) | Traverse course prerequisites |

## Two New Functionalities

### 1. Automatic GPA Calculation System

**Features:**
- Automatically converts numerical marks to letter grades
- Calculates grade points on 4.0 scale
- Computes weighted GPA: `GPA = Σ(grade_point × credits) / Σ(credits)`
- Updates GPA in real-time when results are added

**Grade Scale:**
| Marks | Grade | Grade Point |
|-------|-------|-------------|
| 90-100 | A+ | 4.0 |
| 85-89 | A | 4.0 |
| 80-84 | A- | 3.7 |
| 75-79 | B+ | 3.3 |
| 70-74 | B | 3.0 |
| 65-69 | B- | 2.7 |
| 60-64 | C+ | 2.3 |
| 55-59 | C | 2.0 |
| 50-54 | C- | 1.7 |
| 45-49 | D | 1.0 |
| 0-44 | F | 0.0 |

**Benefits:**
- Eliminates manual grade calculation errors
- Standardized grading across all courses
- Instant feedback on academic performance
- Saves time for administrators

### 2. Course Prerequisite Path Finder

**Algorithm:** Dijkstra's Shortest Path Algorithm

**How It Works:**
- Models courses and prerequisites as a directed graph
- Each course is a vertex (node)
- Prerequisites are directed edges
- Finds the shortest path from target course to foundational courses

**Example:**
```
User Query: Find path from CS401 (Web Development) to CS101 (OOP)
System Output: CS401 → CS301 → CS101

Explanation:
- To take CS401, you need CS301 (Database Systems)
- To take CS301, you need CS101 (OOP)
- Shortest prerequisite chain has 2 intermediate steps
```

**Benefits:**
- Helps students plan optimal course sequence
- Identifies all required prerequisites efficiently
- Prevents enrollment in courses without prerequisites
- Visualizes learning path clearly

**Time Complexity:** O((V+E) log V) where V = courses, E = prerequisites

##  Technology Stack

- **Programming Language:** Java 8+
- **GUI Framework:** Java Swing
- **IDE:** NetBeans IDE
- **Build Tool:** Apache Ant
- **Data Storage:** Java Serialization (File-based)
- **Version Control:** Git & GitHub

##  Project Structure
```
StudentRecordManagementSystem/
│
├── src/
│   ├── algorithms/
│   │   ├── SearchAlgorithms.java          # Binary search implementation
│   │   ├── SortingAlgorithms.java         # Merge sort, Quick sort
│   │   └── GraphAlgorithms.java           # Dijkstra's, DFS
│   │
│   ├── datastructures/
│   │   ├── BinarySearchTree.java          # BST implementation
│   │   └── StudentHashTable.java          # Hash table with chaining
│   │
│   ├── models/
│   │   ├── Student.java                   # Student entity
│   │   ├── Course.java                    # Course entity
│   │   └── Result.java                    # Result entity with grade logic
│   │
│   ├── services/
│   │   ├── StudentService.java            # Student business logic
│   │   └── CourseService.java             # Course business logic
│   │
│   ├── ui/
│   │   ├── MainApplicationFrame.java      # Main window
│   │   ├── StudentPanel.java              # Student management UI
│   │   ├── ResultPanel.java               # Result management UI
│   │   ├── CoursePanel.java               # Course management UI
│   │   └── ReportPanel.java               # Reports & analytics UI
│   │
│   └── utils/
│       └── DataPersistence.java           # File I/O operations
│
└── nbproject/                              # NetBeans project files
```

##  How to Run

### Prerequisites:
- Java JDK 8 or higher
- NetBeans IDE (recommended)

### Steps:
1. Clone or download this repository
2. Open NetBeans IDE
3. File → Open Project
4. Navigate to project folder and select it
5. Right-click project → Clean and Build (Shift+F11)
6. Right-click project → Run (F6)
7. Application window will open

### First Time Usage:
1. Go to **Students** tab
2. Add students with their information
3. Go to **Results** tab
4. Find a student and add course results
5. Go to **Reports** tab to view analytics
6. Use **Save Data** (Ctrl+S) to persist data

##  Performance Analysis

### Time Complexity Summary:

| Operation | Data Structure | Complexity |
|-----------|---------------|------------|
| Add Student | BST | O(log n) |
| Find Student by ID | Hash Table | O(1) |
| Delete Student | BST + Hash | O(log n) |
| Sort by Name | Quick Sort | O(n log n) |
| Sort by GPA | Merge Sort | O(n log n) |
| Find Prerequisite Path | Dijkstra | O((V+E) log V) |
| Traverse Prerequisites | DFS | O(V + E) |

### Space Complexity:
- BST Storage: O(n)
- Hash Table Storage: O(n)
- Course Graph: O(V + E)

Where: n = number of students, V = courses, E = prerequisites

##  Testing

### Test Coverage:
Student CRUD operations  
Result management with GPA calculation  
Search functionality (by ID, name, department)  
Sorting algorithms (by name, by GPA)  
Prerequisite path finding (Dijkstra's)  
Course dependency traversal (DFS)  
Report generation (all 4 types)  
Data persistence (save/load)  

### Sample Test Data:
- 6 students from CS and IT departments
- 5 courses with prerequisite relationships
- Multiple results per student
- GPA range: 0.0 - 4.0


## Course Information

- **Course:** Programming Data Structures and Algorithms - I (PDSA-I)

- **Academic Year:** 2025/2026
- **Submission Date:**  15 February 2026

##  Learning Outcomes Achieved

 Implemented and analyzed Binary Search Trees  
 Designed custom Hash Table with collision handling  
 Applied sorting algorithms in real-world context  
 Utilized graph algorithms for practical problems  
 Developed complete GUI application using Java Swing  
 Implemented file-based data persistence  
 Analyzed algorithm time and space complexity  

## License

This project was developed for educational purposes as part of PDSA coursework at NIBM.
