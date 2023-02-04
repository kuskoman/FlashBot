package bot

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.requests.GatewayIntent

import utils.EnvUtils

class FlashBotFactory {

    private static JDA jda

    static JDA getStartedBot() {
        if (jda == null) {
            def discordToken = EnvUtils.getEnv('DISCORD_BOT_TOKEN')
            jda = JDABuilder.createDefault(discordToken,
              GatewayIntent.GUILD_MESSAGES,
              GatewayIntent.MESSAGE_CONTENT,
              GatewayIntent.GUILD_VOICE_STATES
            )
        .addEventListeners(new FlashBot())
        .build()
        }
        return jda
    }

    static void stopBot() {
        if (jda != null) {
            jda.shutdown()
            jda = null
        }
    }

}
