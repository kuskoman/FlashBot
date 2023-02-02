package bot

import net.dv8tion.jda.api.JDABuilder
import utils.EnvUtils

class FlashBotFactory {
  private static FlashBot bot

  static FlashBot getStartedBot() {
    if (bot == null) {
      def discordToken = EnvUtils.getEnv("DISCORD_BOT_TOKEN")
      def jda = new JDABuilder(discordToken)
        .addEventListeners(new FlashBot())
        .build()
      bot = new FlashBot()
    }
    return bot
  }

  static void stopBot() {
    if (bot != null) {
      bot.jda.shutdown()
      bot = null
    }
  }
}
