package com.designpatterns.oop;

/**
 * Inheritance Example
 * Demonstrates parent-child class relationships
 */
abstract class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void makeSound();

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
}

class Cat extends Animal {
    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    @Override
    void makeSound() {
        System.out.println("Meow!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Indoor: " + isIndoor);
    }
}

/**
 * Polymorphism Example
 * Demonstrates method overriding and interface implementation
 */
interface Shape {
    double calculateArea();
    double calculatePerimeter();
    void display();
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void display() {
        System.out.println("Circle with radius: " + radius);
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void display() {
        System.out.println("Rectangle: " + width + " x " + height);
    }
}

/**
 * Encapsulation Example
 * Demonstrates data hiding and controlled access
 */
class BankAccount {
    private String accountNumber;
    private double balance;
    private String accountHolder;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountInfo() {
        return "Account: " + accountNumber + ", Holder: " + accountHolder;
    }
}

/**
 * Abstraction Example
 * Demonstrates creating abstract models
 */
abstract class Vehicle {
    protected String brand;
    protected String color;

    public Vehicle(String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

    abstract void drive();
    abstract void brake();

    public void honk() {
        System.out.println("Horn sound!");
    }
}

class Car extends Vehicle {
    private int doors;

    public Car(String brand, String color, int doors) {
        super(brand, color);
        this.doors = doors;
    }

    @Override
    void drive() {
        System.out.println("Car is driving...");
    }

    @Override
    void brake() {
        System.out.println("Car is braking...");
    }

    public void openTrunk() {
        System.out.println("Trunk opened");
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, String color, boolean hasSidecar) {
        super(brand, color);
        this.hasSidecar = hasSidecar;
    }

    @Override
    void drive() {
        System.out.println("Motorcycle is riding...");
    }

    @Override
    void brake() {
        System.out.println("Motorcycle is braking...");
    }
}

/**
 * Main class to demonstrate OOP concepts
 */
public class OopExamples {
    public static void demonstrateOop() {
        // Inheritance and Polymorphism
        System.out.println("--- Inheritance Example ---");
        Animal dog = new Dog("Rex", 5, "Golden Retriever");
        dog.makeSound();
        dog.displayInfo();

        System.out.println();
        Animal cat = new Cat("Whiskers", 3, true);
        cat.makeSound();
        cat.displayInfo();

        // Polymorphism with Shapes
        System.out.println("\n--- Polymorphism Example ---");
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println("Circle - Area: " + String.format("%.2f", circle.calculateArea()));
        System.out.println("Rectangle - Area: " + rectangle.calculateArea());

        // Encapsulation
        System.out.println("\n--- Encapsulation Example ---");
        BankAccount account = new BankAccount("ACC001", "John Doe");
        System.out.println(account.getAccountInfo());
        account.deposit(1000);
        account.withdraw(500);
        System.out.println("Current balance: $" + account.getBalance());

        // Abstraction
        System.out.println("\n--- Abstraction Example ---");
        Vehicle car = new Car("Toyota", "Red", 4);
        car.drive();
        car.brake();
        car.honk();
    }
}
