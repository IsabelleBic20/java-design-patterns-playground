package com.designpatterns;

import com.designpatterns.oop.OopExamples;
import com.designpatterns.solid.SolidExamples;
import com.designpatterns.collections.CollectionsExamples;
import com.designpatterns.streams.StreamsExamples;
import com.designpatterns.generics.GenericsExamples;
import com.designpatterns.exceptions.ExceptionExamples;
import com.designpatterns.patterns.DesignPatternsExamples;
import com.designpatterns.concurrency.ConcurrencyExamples;

/**
 * Main entry point for the Java Design Patterns Playground
 * Demonstrates advanced Java concepts and design patterns
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Java Design Patterns Playground ===\n");

        System.out.println("1. OOP Examples");
        System.out.println("================");
        OopExamples.demonstrateOop();

        System.out.println("\n2. SOLID Principles");
        System.out.println("===================");
        SolidExamples.demonstrateSolid();

        System.out.println("\n3. Collections Examples");
        System.out.println("======================");
        CollectionsExamples.demonstrateCollections();

        System.out.println("\n4. Streams Examples");
        System.out.println("===================");
        StreamsExamples.demonstrateStreams();

        System.out.println("\n5. Generics Examples");
        System.out.println("====================");
        GenericsExamples.demonstrateGenerics();

        System.out.println("\n6. Exception Handling");
        System.out.println("====================");
        ExceptionExamples.demonstrateExceptions();

        System.out.println("\n7. Design Patterns");
        System.out.println("==================");
        DesignPatternsExamples.demonstratePatterns();

        System.out.println("\n8. Concurrency Examples");
        System.out.println("======================");
        ConcurrencyExamples.demonstrateConcurrency();

        System.out.println("\n=== Demonstration Complete ===");
    }
}
