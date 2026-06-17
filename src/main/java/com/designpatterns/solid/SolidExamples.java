package com.designpatterns.solid;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLID Principles - Advanced Examples
 * S - Single Responsibility Principle
 * O - Open/Closed Principle
 * L - Liskov Substitution Principle
 * I - Interface Segregation Principle
 * D - Dependency Inversion Principle
 */

// ============================================================
// 1. SINGLE RESPONSIBILITY PRINCIPLE (SRP)
// ============================================================

// BAD: UserService has multiple responsibilities
class BadUserService {
    public void createUser(String name, String email) {
        // Create user logic
        System.out.println("Creating user: " + name);
    }

    public void sendWelcomeEmail(String email) {
        // Email logic
        System.out.println("Sending email to: " + email);
    }

    public void logActivity(String activity) {
        // Logging logic
        System.out.println("Logging: " + activity);
    }
}

// GOOD: Separated concerns
class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}

class UserRepository {
    public void save(User user) {
        System.out.println("Saving user: " + user.getName());
    }
}

class EmailService {
    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to: " + email);
    }
}

class UserActivityLogger {
    public void log(String activity) {
        System.out.println("Activity logged: " + activity);
    }
}

// ============================================================
// 2. OPEN/CLOSED PRINCIPLE (OCP)
// ============================================================

// BAD: Not open for extension without modification
class BadNotificationService {
    public void sendNotification(String type, String message) {
        if ("EMAIL".equals(type)) {
            System.out.println("Sending email: " + message);
        } else if ("SMS".equals(type)) {
            System.out.println("Sending SMS: " + message);
        }
        // Adding new notification types requires modifying this class
    }
}

// GOOD: Open for extension, closed for modification
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
}

class GoodNotificationService {
    private Notification notification;

    public GoodNotificationService(Notification notification) {
        this.notification = notification;
    }

    public void notify(String message) {
        notification.send(message);
    }
}

// ============================================================
// 3. LISKOV SUBSTITUTION PRINCIPLE (LSP)
// ============================================================

// BAD: Rectangle violates LSP
class BadRectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public int getArea() { return width * height; }
}

class BadSquare extends BadRectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // Forces square behavior
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        this.width = height; // Forces square behavior
    }
}

// GOOD: Proper inheritance hierarchy
abstract class Shape {
    abstract int getArea();
}

class Rectangle extends Shape {
    protected int width;
    protected int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}

class Square extends Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int getArea() {
        return side * side;
    }
}

// ============================================================
// 4. INTERFACE SEGREGATION PRINCIPLE (ISP)
// ============================================================

// BAD: Fat interface
interface BadWorker {
    void work();
    void eat();
    void sleep();
    void drive();
}

// GOOD: Segregated interfaces
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Drivable {
    void drive();
}

class Employee implements Workable, Eatable {
    @Override
    public void work() {
        System.out.println("Employee is working");
    }

    @Override
    public void eat() {
        System.out.println("Employee is eating");
    }
}

class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}

// ============================================================
// 5. DEPENDENCY INVERSION PRINCIPLE (DIP)
// ============================================================

// BAD: High-level depends on low-level
class BadDataService {
    private MySqlDatabase database = new MySqlDatabase();

    public void saveData(String data) {
        database.save(data);
    }
}

class MySqlDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// GOOD: Both depend on abstraction
interface Database {
    void save(String data);
}

class MySqlDatabaseGood implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class MongoDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
}

class GoodDataService {
    private Database database;

    public GoodDataService(Database database) {
        this.database = database;
    }

    public void saveData(String data) {
        database.save(data);
    }
}

/**
 * Main class to demonstrate SOLID principles
 */
public class SolidExamples {
    public static void demonstrateSolid() {
        // 1. SRP Example
        System.out.println("--- Single Responsibility Principle ---");
        User user = new User("John Doe", "john@example.com");
        UserRepository userRepo = new UserRepository();
        EmailService emailService = new EmailService();
        UserActivityLogger logger = new UserActivityLogger();

        userRepo.save(user);
        emailService.sendWelcomeEmail(user.getEmail());
        logger.log("User created: " + user.getName());

        // 2. OCP Example
        System.out.println("\n--- Open/Closed Principle ---");
        GoodNotificationService emailNotifier = new GoodNotificationService(new EmailNotification());
        GoodNotificationService smsNotifier = new GoodNotificationService(new SmsNotification());
        GoodNotificationService pushNotifier = new GoodNotificationService(new PushNotification());

        emailNotifier.notify("Hello via Email");
        smsNotifier.notify("Hello via SMS");
        pushNotifier.notify("Hello via Push");

        // 3. LSP Example
        System.out.println("\n--- Liskov Substitution Principle ---");
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(5, 4));
        shapes.add(new Square(5));

        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.getArea());
        }

        // 4. ISP Example
        System.out.println("\n--- Interface Segregation Principle ---");
        Workable employee = new Employee();
        Workable robot = new Robot();

        employee.work();
        robot.work();

        if (employee instanceof Eatable eatable) {
            eatable.eat();
        }

        // 5. DIP Example
        System.out.println("\n--- Dependency Inversion Principle ---");
        Database mySqlDb = new MySqlDatabaseGood();
        Database mongoDb = new MongoDatabase();

        GoodDataService mysqlService = new GoodDataService(mySqlDb);
        GoodDataService mongoService = new GoodDataService(mongoDb);

        mysqlService.saveData("Important Data");
        mongoService.saveData("Document Data");
    }
}
