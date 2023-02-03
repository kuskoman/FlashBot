package bot.utils

import static org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EnvUtilsTest {

    @Test
    void testGetEnvFromSystemVariables() {
        System.setProperty("testkey", "testvalue")
        assertEquals("testvalue", EnvUtils.getEnv("testkey"))
    }

    @Test
    void testGetEnvFromDotenv() {
        EnvUtils.dotenv.getProperties().put("testkey", "testvalue")
        assertEquals("testvalue", EnvUtils.getEnv("testkey"))
    }

    @Test
    void testGetEnvRequiredTrue() {
        assertThrows(RuntimeException) { EnvUtils.getEnv("non_existent_key") }
    }

    @Test
    void testGetEnvRequiredFalse() {
        assertEquals(null, EnvUtils.getEnv("non_existent_key", false))
    }
}
