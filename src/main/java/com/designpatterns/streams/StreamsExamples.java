package com.designpatterns.streams;

import java.util.*;
import java.util.stream.*;

/**
 * Streams Examples - filter, map, reduce, groupingBy, sorting
 */
public class StreamsExamples {

    static class Person {
        String name;
        int age;
        String department;
        double salary;

        public Person(String name, int age, String department, double salary) {
            this.name = name;
            this.age = age;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + " (" + age + ", " + department + ", $" + salary + ")";
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }

    static class FilterExample {
        public static void demonstrate() {
            System.out.println("--- Filter Example ---");
            List<Person> people = Arrays.asList(
                new Person("Alice", 28, "Engineering", 75000),
                new Person("Bob", 35, "Sales", 65000),
                new Person("Charlie", 24, "Engineering", 55000),
                new Person("Diana", 31, "Marketing", 60000),
                new Person("Eve", 27, "Engineering", 70000)
            );

            // Filter by age
            System.out.println("People over 25:");
            people.stream()
                .filter(p -> p.getAge() > 25)
                .forEach(System.out::println);

            // Filter by department
            System.out.println("\nEngineers:");
            people.stream()
                .filter(p -> "Engineering".equals(p.getDepartment()))
                .forEach(System.out::println);

            // Multiple filters
            System.out.println("\nEngineers over 25:");
            people.stream()
                .filter(p -> "Engineering".equals(p.getDepartment()))
                .filter(p -> p.getAge() > 25)
                .forEach(System.out::println);
        }
    }

    static class MapExample {
        public static void demonstrate() {
            System.out.println("\n--- Map Example ---");
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

            // Map to squares
            System.out.println("Squares:");
            numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

            // Map to strings
            System.out.println("\nAs strings:");
            numbers.stream()
                .map(String::valueOf)
                .forEach(System.out::println);

            // FlatMap example
            System.out.println("\nFlatMap example:");
            List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
            );

            nestedList.stream()
                .flatMap(List::stream)
                .map(n -> n * 2)
                .forEach(System.out::println);

            // Extract names
            System.out.println("\nPerson names:");
            List<Person> people = Arrays.asList(
                new Person("Alice", 28, "Engineering", 75000),
                new Person("Bob", 35, "Sales", 65000),
                new Person("Charlie", 24, "Engineering", 55000)
            );

            people.stream()
                .map(Person::getName)
                .forEach(System.out::println);
        }
    }

    static class ReduceExample {
        public static void demonstrate() {
            System.out.println("\n--- Reduce Example ---");
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

            // Sum
            int sum = numbers.stream()
                .reduce(0, Integer::sum);
            System.out.println("Sum: " + sum);

            // Product
            int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
            System.out.println("Product: " + product);

            // Max
            Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
            System.out.println("Max: " + max.orElse(-1));

            // Min
            Optional<Integer> min = numbers.stream()
                .reduce(Integer::min);
            System.out.println("Min: " + min.orElse(-1));

            // Concat strings
            List<String> words = Arrays.asList("Hello", "Stream", "World");
            String result = words.stream()
                .reduce("", (a, b) -> a + " " + b)
                .trim();
            System.out.println("Concatenated: " + result);

            // Average salary
            List<Person> people = Arrays.asList(
                new Person("Alice", 28, "Engineering", 75000),
                new Person("Bob", 35, "Sales", 65000),
                new Person("Charlie", 24, "Engineering", 55000)
            );

            double avgSalary = people.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0);
            System.out.println("Average salary: $" + avgSalary);
        }
    }

    static class GroupingByExample {
        public static void demonstrate() {
            System.out.println("\n--- GroupingBy Example ---");
            List<Person> people = Arrays.asList(
                new Person("Alice", 28, "Engineering", 75000),
                new Person("Bob", 35, "Sales", 65000),
                new Person("Charlie", 24, "Engineering", 55000),
                new Person("Diana", 31, "Marketing", 60000),
                new Person("Eve", 27, "Engineering", 70000),
                new Person("Frank", 29, "Sales", 62000)
            );

            // Group by department
            Map<String, List<Person>> byDept = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
            System.out.println("By Department:");
            byDept.forEach((dept, employees) ->
                System.out.println("  " + dept + ": " + employees)
            );

            // Count by department
            Map<String, Long> countByDept = people.stream()
                .collect(Collectors.groupingBy(
                    Person::getDepartment,
                    Collectors.counting()
                ));
            System.out.println("\nCount by Department:");
            countByDept.forEach((dept, count) ->
                System.out.println("  " + dept + ": " + count)
            );

            // Average salary by department
            Map<String, Double> avgSalaryByDept = people.stream()
                .collect(Collectors.groupingBy(
                    Person::getDepartment,
                    Collectors.averagingDouble(Person::getSalary)
                ));
            System.out.println("\nAverage Salary by Department:");
            avgSalaryByDept.forEach((dept, salary) ->
                System.out.println("  " + dept + ": $" + salary)
            );

            // Group and partition
            Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getSalary() > 65000));
            System.out.println("\nPartitioned by salary > 65000:");
            System.out.println("  High salary: " + partitioned.get(true).size() + " people");
            System.out.println("  Lower salary: " + partitioned.get(false).size() + " people");
        }
    }

    static class SortingExample {
        public static void demonstrate() {
            System.out.println("\n--- Sorting Example ---");
            List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

            System.out.println("Original: " + numbers);

            // Sort ascending
            System.out.println("Sorted ascending:");
            numbers.stream()
                .sorted()
                .forEach(System.out::println);

            // Sort descending
            System.out.println("Sorted descending:");
            numbers.stream()
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);

            // Sort custom objects
            System.out.println("\nSort people by age:");
            List<Person> people = Arrays.asList(
                new Person("Alice", 28, "Engineering", 75000),
                new Person("Bob", 35, "Sales", 65000),
                new Person("Charlie", 24, "Engineering", 55000),
                new Person("Diana", 31, "Marketing", 60000)
            );

            people.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(System.out::println);

            System.out.println("\nSort people by salary (descending):");
            people.stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .forEach(System.out::println);

            System.out.println("\nSort people by department then age:");
            people.stream()
                .sorted(Comparator.comparing(Person::getDepartment)
                    .thenComparingInt(Person::getAge))
                .forEach(System.out::println);
        }
    }

    static class StreamOperationsExample {
        public static void demonstrate() {
            System.out.println("\n--- Stream Operations Example ---");
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            // count
            long count = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
            System.out.println("Count of even numbers: " + count);

            // distinct
            List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
            System.out.println("Distinct: " +
                duplicates.stream()
                    .distinct()
                    .collect(Collectors.toList())
            );

            // limit and skip
            System.out.println("Limit 3: " +
                numbers.stream()
                    .limit(3)
                    .collect(Collectors.toList())
            );

            System.out.println("Skip 7: " +
                numbers.stream()
                    .skip(7)
                    .collect(Collectors.toList())
            );

            // collect to various types
            List<Integer> list = numbers.stream()
                .filter(n -> n > 5)
                .collect(Collectors.toList());
            System.out.println("As List: " + list);

            Set<Integer> set = numbers.stream()
                .filter(n -> n > 5)
                .collect(Collectors.toSet());
            System.out.println("As Set: " + set);
        }
    }

    public static void demonstrateStreams() {
        FilterExample.demonstrate();
        MapExample.demonstrate();
        ReduceExample.demonstrate();
        GroupingByExample.demonstrate();
        SortingExample.demonstrate();
        StreamOperationsExample.demonstrate();
    }
}
