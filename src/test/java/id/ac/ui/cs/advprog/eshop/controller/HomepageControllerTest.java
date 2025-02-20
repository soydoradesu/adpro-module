// File: HomepageControllerTest.java
package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomepageControllerTest {
    @InjectMocks
    private HomepageController homepageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHomepage() {
        String viewName = homepageController.homepage();
        assertEquals("Homepage", viewName);
    }
}