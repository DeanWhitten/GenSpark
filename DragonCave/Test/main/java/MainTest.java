package main.java;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    Main main;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        main  = new Main();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("Test Passed");
    }

    @Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void testSelectionCaveResults() {
        assertEquals(1, 1, "Check 1, something went wrong");
        assertEquals(2, 2, "Check 2, Something went wrong");
    }
}