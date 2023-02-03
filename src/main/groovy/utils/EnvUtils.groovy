package utils

import io.github.cdimascio.dotenv.Dotenv

class EnvUtils {
  private static Dotenv dotenv

  static {
    dotenv = Dotenv.configure().ignoreIfMissing().load()
  }

  static String getEnv(String key, boolean required = true) {
    def value = System.getenv(key) ?: dotenv.get(key)
    if (value == null && required) {
      throw new RuntimeException("Environment variable $key is required")
    }
    return value
  }
}
