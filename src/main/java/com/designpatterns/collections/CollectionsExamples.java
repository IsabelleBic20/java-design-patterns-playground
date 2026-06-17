package com.designpatterns.collections;

import java.util.*;

/**
 * Collections Examples - ArrayList, HashMap, HashSet, TreeMap
 */
public class CollectionsExamples {

    /**
     * ArrayList Example - Dynamic array implementation
     */
    static class ArrayListDemo {
        public static void demonstrate() {
            System.out.println("--- ArrayList Example ---");
            List<String> fruits = new ArrayList<>();

            // Add elements
            fruits.add("Apple");
            fruits.add("Banana");
            fruits.add("Orange");
            fruits.add("Mango");

            System.out.println("Fruits: " + fruits);
            System.out.println("Size: " + fruits.size());

            // Access elements
            System.out.println("First fruit: " + fruits.get(0));

            // Modify elements
            fruits.set(1, "Blueberry");
            System.out.println("After modification: " + fruits);

            // Iterate
            System.out.println("Iterating:");
            for (String fruit : fruits) {
                System.out.println("  - " + fruit);
            }

            // Remove elements
            fruits.remove("Orange");
            System.out.println("After removal: " + fruits);

            // Check contains
            System.out.println("Contains 'Apple': " + fruits.contains("Apple"));

            // Clear
            // fruits.clear();
        }
    }

    /**
     * HashMap Example - Key-value mapping
     */
    static class HashMapDemo {
        public static void demonstrate() {
            System.out.println("\n--- HashMap Example ---");
            Map<String, Integer> scores = new HashMap<>();

            // Add key-value pairs
            scores.put("Alice", 95);
            scores.put("Bob", 87);
            scores.put("Charlie", 92);
            scores.put("Diana", 88);

            System.out.println("Scores: " + scores);
            System.out.println("Size: " + scores.size());

            // Access values
            System.out.println("Bob's score: " + scores.get("Bob"));

            // Check contains
            System.out.println("Contains 'Alice': " + scores.containsKey("Alice"));
            System.out.println("Contains 95: " + scores.containsValue(95));

            // Iterate over entries
            System.out.println("All scores:");
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }

            // Update value
            scores.put("Alice", 98);
            System.out.println("Alice's updated score: " + scores.get("Alice"));

            // Remove entry
            scores.remove("Bob");
            System.out.println("After removing Bob: " + scores);

            // Get all keys
            System.out.println("All students: " + scores.keySet());

            // Get all values
            System.out.println("All scores values: " + scores.values());
        }
    }

    /**
     * HashSet Example - Unique elements collection
     */
    static class HashSetDemo {
        public static void demonstrate() {
            System.out.println("\n--- HashSet Example ---");
            Set<String> colors = new HashSet<>();

            // Add elements
            colors.add("Red");
            colors.add("Blue");
            colors.add("Green");
            colors.add("Red"); // Duplicate, won't be added
            colors.add("Yellow");

            System.out.println("Colors: " + colors);
            System.out.println("Size: " + colors.size());

            // Check contains
            System.out.println("Contains 'Blue': " + colors.contains("Blue"));

            // Iterate
            System.out.println("Iterating:");
            for (String color : colors) {
                System.out.println("  - " + color);
            }

            // Set operations
            Set<String> moreColors = new HashSet<>();
            moreColors.add("Red");
            moreColors.add("Purple");
            moreColors.add("Pink");

            // Union
            Set<String> union = new HashSet<>(colors);
            union.addAll(moreColors);
            System.out.println("Union: " + union);

            // Intersection
            Set<String> intersection = new HashSet<>(colors);
            intersection.retainAll(moreColors);
            System.out.println("Intersection: " + intersection);

            // Difference
            Set<String> difference = new HashSet<>(colors);
            difference.removeAll(moreColors);
            System.out.println("Difference: " + difference);

            // Remove
            colors.remove("Green");
            System.out.println("After removal: " + colors);
        }
    }

    /**
     * TreeMap Example - Sorted key-value mapping
     */
    static class TreeMapDemo {
        public static void demonstrate() {
            System.out.println("\n--- TreeMap Example ---");
            Map<String, Double> temperatures = new TreeMap<>();

            temperatures.put("March", 15.5);
            temperatures.put("January", 5.2);
            temperatures.put("December", 8.3);
            temperatures.put("April", 18.7);
            temperatures.put("February", 6.1);

            System.out.println("Temperatures (sorted by key): " + temperatures);

            // First and last
            if (temperatures instanceof TreeMap) {
                TreeMap<String, Double> treeMap = (TreeMap<String, Double>) temperatures;
                System.out.println("First month: " + treeMap.firstKey() + " -> " + treeMap.firstEntry());
                System.out.println("Last month: " + treeMap.lastKey() + " -> " + treeMap.lastEntry());

                // Sub maps
                System.out.println("Months from January to March: " +
                    treeMap.subMap("January", "April"));

                // Head map
                System.out.println("Months before April: " + treeMap.headMap("April"));

                // Tail map
                System.out.println("Months from March onwards: " + treeMap.tailMap("March"));
            }

            // Iterate in sorted order
            System.out.println("All temperatures (sorted):");
            for (Map.Entry<String, Double> entry : temperatures.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue() + "°C");
            }

            // ComparableComparator
            Map<Integer, String> numbers = new TreeMap<>(Collections.reverseOrder());
            numbers.put(1, "One");
            numbers.put(3, "Three");
            numbers.put(2, "Two");
            System.out.println("Numbers (reverse sorted): " + numbers);
        }
    }

    /**
     * Combined Complex Example
     */
    static class ComplexExample {
        static class Student {
            String name;
            int grade;
            Set<String> courses;

            public Student(String name, int grade) {
                this.name = name;
                this.grade = grade;
                this.courses = new HashSet<>();
            }

            @Override
            public String toString() {
                return name + " (Grade: " + grade + ")";
            }
        }

        public static void demonstrate() {
            System.out.println("\n--- Complex Collections Example ---");

            List<Student> students = new ArrayList<>();
            Map<String, List<Student>> classRooms = new HashMap<>();
            TreeMap<Integer, Set<Student>> gradeDistribution = new TreeMap<>();

            // Create students
            Student s1 = new Student("Alice", 10);
            s1.courses.addAll(Arrays.asList("Math", "English", "Science"));
            Student s2 = new Student("Bob", 10);
            s2.courses.addAll(Arrays.asList("Math", "PE", "Art"));
            Student s3 = new Student("Charlie", 9);
            s3.courses.addAll(Arrays.asList("English", "History", "Science"));

            students.addAll(Arrays.asList(s1, s2, s3));

            // Group by classroom
            for (Student student : students) {
                String classroom = "Class-" + student.grade;
                classRooms.computeIfAbsent(classroom, k -> new ArrayList<>()).add(student);
            }

            System.out.println("Classrooms: " + classRooms);

            // Group by grade
            for (Student student : students) {
                gradeDistribution.computeIfAbsent(student.grade, k -> new HashSet<>()).add(student);
            }

            System.out.println("Grade Distribution: " + gradeDistribution);
        }
    }

    public static void demonstrateCollections() {
        ArrayListDemo.demonstrate();
        HashMapDemo.demonstrate();
        HashSetDemo.demonstrate();
        TreeMapDemo.demonstrate();
        ComplexExample.demonstrate();
    }
}
