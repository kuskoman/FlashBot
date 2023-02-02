package utils

class EnvUtils {
  static String getEnv(String key, boolean required = true) {
    def value = System.getenv(key)
    if (value == null && required) {
      throw new RuntimeException("Environment variable $key is required")
    }
    return value
  }
}
