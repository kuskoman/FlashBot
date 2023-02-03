package utils

import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EnvUtilsTest {
    @Test
    void testGetEnvRequiredTrue() {
        assertThrows(RuntimeException) { EnvUtils.getEnv("non_existent_key") }
    }

    @Test
    void testGetEnvRequiredFalse() {
        assertEquals(null, EnvUtils.getEnv("non_existent_key", false))
    }
}
