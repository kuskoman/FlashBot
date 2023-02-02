package bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface Command {
  String getName()
  String getDescription()
  String getUsage()
  void execute(MessageReceivedEvent event)
}
