package bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import bot.handler.CommandHandlerFactory

class ListCommandsCommand implements Command {

  @Override
  String getName() {
    return "list"
  }

  @Override
  String getDescription() {
    return "Lists all commands"
  }

  @Override
  String getUsage() {
    return "list"
  }

  @Override
  void execute(MessageReceivedEvent event) {
    def handler = CommandHandlerFactory.getHandler()
    def commands = handler.getCommands()
    def message = "Commands: "
    message += commands.map { command -> message += command.getName() }.join(", ")
    message = message.substring(0, message.length() - 2)
    event.getChannel().sendMessage(message).queue()
  }
}
