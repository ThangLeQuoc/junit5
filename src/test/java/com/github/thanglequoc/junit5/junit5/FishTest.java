package com.github.thanglequoc.junit5.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Fish Test Cases")
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
    @DisplayName("╯°□°）╯ OMG I'm about to test a shark")
    public void testFishCreation() {
        Fish fish = new Fish();
        String fishName = "Shark";
        fish.setName(fishName);
        assertEquals(fishName, fish.getName());
    }
    
    @Disabled
    public void testDisableAnnotation() {
        System.out.println("This test will not be executed");
    }
    
    @Test
    public void testFishAllAssertion() {
        // given
        String name = "Golden Fish";
        int price = 10;
        
        //when
        Fish fish = new Fish();
        fish.setName(name);
        fish.setPrice(price);
        
        // then
        assertAll("Fish Creation", () -> assertEquals(name, fish.getName()),
                () -> assertEquals(price, fish.getPrice()));
        
        assertEquals(name, fish.getName());
        assertEquals(price, fish.getClass());
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testSetPrice_ShouldThrowException_JUnit4Expression() {
        int price = -1;
        Fish fish = new Fish();
        
        try {
            fish.setPrice(price);
            fail("should throw exception earlier");
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal Price", e.getMessage());
            throw e;
        }
    }
    
    @Test
    public void testSetPrice_ShouldThrowException_JUnit5Expression() {
        int price = -1;
        Fish fish = new Fish();
        Throwable actualException = assertThrows(IllegalArgumentException.class, () -> {
            fish.setPrice(price);
        });
        assertEquals("Illegal Price", actualException.getMessage());
    }
}
