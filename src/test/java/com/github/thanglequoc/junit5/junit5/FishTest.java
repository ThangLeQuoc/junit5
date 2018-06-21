package com.github.thanglequoc.junit5.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FishTest {
    
    @BeforeAll
    public static void setUp() {
        System.out.println("To be printed before all test");
    }
    
    @BeforeEach
    public void init() {
        System.out.println("To be printed before each test execution");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("To be printed after each test execution");
    }
    
    @AfterAll
    public static void done() {
        System.out.println("To be printed after all tests execution");
    }
    
    @Test
    public void testFishCreation() {
        Fish fish = new Fish();
        String fishName = "Shark";
        fish.setName(fishName);
        assertEquals(fishName, fish.getName());
    }
    
}
