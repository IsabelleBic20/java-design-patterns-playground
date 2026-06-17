package com.designpatterns.generics;

import java.util.*;

/**
 * Generics Examples - Generic classes and methods
 */
public class GenericsExamples {

    /**
     * Generic Box class - stores any type of object
     */
    static class Box<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public boolean isEmpty() {
            return value == null;
        }

        @Override
        public String toString() {
            return value != null ? value.toString() : "Empty";
        }
    }

    /**
     * Generic Pair class - stores two values of different types
     */
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() {
            return key + " -> " + value;
        }
    }

    /**
     * Generic List wrapper
     */
    static class GenericList<T> {
        private List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        public T get(int index) {
            return items.get(index);
        }

        public int size() {
            return items.size();
        }

        public void printAll() {
            for (T item : items) {
                System.out.println("  " + item);
            }
        }
    }

    /**
     * Generic Stack implementation
     */
    static class Stack<T> {
        private List<T> elements = new ArrayList<>();

        public void push(T element) {
            elements.add(element);
        }

        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return elements.remove(elements.size() - 1);
        }

        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

    /**
     * Generic methods - with bounded type parameters
     */
    static class GenericMethods {
        // Simple generic method
        public static <T> void printArray(T[] array) {
            for (T element : array) {
                System.out.println("  " + element);
            }
        }

        // Generic method with bounded type parameter
        public static <T extends Number> double sum(T[] numbers) {
            double total = 0;
            for (T number : numbers) {
                total += number.doubleValue();
            }
            return total;
        }

        // Generic method with upper bound
        public static <T extends Comparable<T>> T getMax(T[] array) {
            T max = array[0];
            for (T element : array) {
                if (element.compareTo(max) > 0) {
                    max = element;
                }
            }
            return max;
        }

        // Generic method returning generic type
        public static <T, R> R convert(T value, Class<R> targetClass) {
            System.out.println("Converting " + value.getClass().getSimpleName() +
                " to " + targetClass.getSimpleName());
            return null; // Simplified for example
        }

        // Generic method with multiple type parameters
        public static <T, U> Pair<T, U> makePair(T first, U second) {
            return new Pair<>(first, second);
        }

        // Generic method with wildcards
        public static void printList(List<?> list) {
            System.out.println("List contains:");
            for (Object item : list) {
                System.out.println("  " + item);
            }
        }

        // Method with bounded wildcard - accepts List of Number or its subclasses
        public static double sumNumbers(List<? extends Number> numbers) {
            double total = 0;
            for (Number num : numbers) {
                total += num.doubleValue();
            }
            return total;
        }

        // Method that writes to collection (lower bound)
        public static void fillNumbers(List<? super Integer> numbers) {
            for (int i = 1; i <= 5; i++) {
                numbers.add(i);
            }
        }
    }

    /**
     * Bounded type parameter example
     */
    static class Animal implements Comparable<Animal> {
        String name;
        int age;

        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Animal other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return name + " (age: " + age + ")";
        }
    }

    static class Dog extends Animal {
        String breed;

        public Dog(String name, int age, String breed) {
            super(name, age);
            this.breed = breed;
        }

        @Override
        public String toString() {
            return name + " - " + breed + " (age: " + age + ")";
        }
    }

    /**
     * Generic Container with bounded type
     */
    static class AnimalContainer<T extends Animal> {
        private List<T> animals = new ArrayList<>();

        public void add(T animal) {
            animals.add(animal);
        }

        public void printAll() {
            System.out.println("Animals in container:");
            for (T animal : animals) {
                System.out.println("  " + animal);
            }
        }

        public T getOldest() {
            if (animals.isEmpty()) return null;
            T oldest = animals.get(0);
            for (T animal : animals) {
                if (animal.age > oldest.age) {
                    oldest = animal;
                }
            }
            return oldest;
        }
    }

    public static void demonstrateGenerics() {
        // Generic Box class
        System.out.println("--- Generic Box ---");
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generic");
        System.out.println("String box: " + stringBox);

        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println("Integer box: " + intBox);

        // Generic Pair
        System.out.println("\n--- Generic Pair ---");
        Pair<String, Integer> pair = new Pair<>("Age", 30);
        System.out.println("Pair: " + pair);

        // Generic List
        System.out.println("\n--- Generic List ---");
        GenericList<String> colors = new GenericList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        System.out.println("Colors:");
        colors.printAll();

        // Generic Stack
        System.out.println("\n--- Generic Stack ---");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        // Generic methods
        System.out.println("\n--- Generic Methods ---");
        String[] strings = {"Hello", "World", "Java"};
        System.out.println("String array:");
        GenericMethods.printArray(strings);

        Integer[] integers = {1, 2, 3, 4, 5};
        System.out.println("Sum of integers: " + GenericMethods.sum(integers));

        Double[] doubles = {1.5, 2.7, 3.2, 0.8};
        System.out.println("Sum of doubles: " + GenericMethods.sum(doubles));

        System.out.println("Max string: " + GenericMethods.getMax(strings));
        System.out.println("Max integer: " + GenericMethods.getMax(integers));

        // Pair from generic method
        System.out.println("\n--- Pair from Generic Method ---");
        Pair<String, Double> tempPair = GenericMethods.makePair("Temperature", 25.5);
        System.out.println("Made pair: " + tempPair);

        // Wildcards
        System.out.println("\n--- Wildcards ---");
        List<String> stringList = Arrays.asList("A", "B", "C");
        GenericMethods.printList(stringList);

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of numbers: " + GenericMethods.sumNumbers(intList));

        List<Number> numList = new ArrayList<>();
        GenericMethods.fillNumbers(numList);
        System.out.println("Filled numbers: " + numList);

        // Bounded type parameters
        System.out.println("\n--- Bounded Type Parameters ---");
        AnimalContainer<Animal> container = new AnimalContainer<>();
        container.add(new Animal("Lion", 5));
        container.add(new Animal("Tiger", 8));
        container.add(new Animal("Bear", 6));
        container.printAll();
        System.out.println("Oldest animal: " + container.getOldest());

        // Dog container (Dog extends Animal)
        System.out.println("\n--- Dog Container ---");
        AnimalContainer<Dog> dogContainer = new AnimalContainer<>();
        dogContainer.add(new Dog("Rex", 4, "German Shepherd"));
        dogContainer.add(new Dog("Buddy", 6, "Golden Retriever"));
        dogContainer.add(new Dog("Max", 3, "Labrador"));
        dogContainer.printAll();
        System.out.println("Oldest dog: " + dogContainer.getOldest());
    }
}
