package bot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import bot.handler.*

class FlashBot extends ListenerAdapter {
  private String prefix;
  private CommandHandler handler;

  FlashBot(String prefix = "!") {
    this.prefix = prefix
    this.handler = CommandHandlerFactory.getHandler()
  }

  String getPrefix() {
    return prefix
  }

  void setPrefix(String prefix) {
    this.prefix = prefix
  }

  @Override
  void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) {
      return
    }
    
    def messageContent = event.getMessage().getContentRaw()
    if (!messageContent.startsWith(prefix)) {
      return
    }

    def command = messageContent.substring(prefix.length()).split(" ")[0]
    def args = messageContent.substring(prefix.length() + command.length()).trim()
    handler.executeCommand(command, event, args)
  }
}

