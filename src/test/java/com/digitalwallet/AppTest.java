package com.digitalwallet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")  // Use test profile
class AppTest {
    @Test
    void contextLoads() {
        // Basic sanity test
        assertTrue(true);
    }
    
    @Test
    void testMainMethod() {
        Application.main(new String[] {});
        assertTrue(true);
    }
}
