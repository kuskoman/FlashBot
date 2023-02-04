package bot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

import utils.EnvUtils

class FlashBotFactory {
  private static FlashBot bot

  static FlashBot getStartedBot() {
    if (bot == null) {
      def discordToken = EnvUtils.getEnv("DISCORD_BOT_TOKEN")
      def jda = JDABuilder.createDefault(discordToken, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
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
