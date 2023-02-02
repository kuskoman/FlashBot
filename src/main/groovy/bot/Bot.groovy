package bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class FlashBot extends ListenerAdapter {
  @Override
  void onMessageReceived(MessageReceivedEvent event) {
    if (event.getMessage().getContentRaw().equals("ping")) {
      event.getChannel().sendMessage("Pong!").queue()
    }
  }
}

