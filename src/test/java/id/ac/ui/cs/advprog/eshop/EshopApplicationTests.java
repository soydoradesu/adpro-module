package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the Spring context loads successfully
    }

    @Test
    void mainMethodStartsApplication() {
        // Test the main method using mockStatic
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            // Set up mock behavior
            ConfigurableApplicationContext context = org.mockito.Mockito.mock(ConfigurableApplicationContext.class);
            mockedSpringApplication.when(() -> SpringApplication.run(
                            eq(EshopApplication.class),
                            any(String[].class)))
                    .thenReturn(context);

            // Call the main method
            EshopApplication.main(new String[]{});

            // Verify that SpringApplication.run was called with the correct parameters
            mockedSpringApplication.verify(() ->
                    SpringApplication.run(EshopApplication.class, new String[]{}));
        }
    }
}