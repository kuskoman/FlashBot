import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import io.github.cdimascio.dotenv.Dotenv

class PingBot extends ListenerAdapter {
  @Override
  void onMessageReceived(MessageReceivedEvent event) {
    if (event.getMessage().getContentRaw().equals("ping")) {
      event.getChannel().sendMessage("Pong!").queue()
    }
  }
}

def dotenv = Dotenv.load();
def discordToken = dotenv.get("DISCORD_BOT_TOKEN")
def jda = new JDABuilder(discordToken)
        .addEventListeners(new PingBot())
        .build()