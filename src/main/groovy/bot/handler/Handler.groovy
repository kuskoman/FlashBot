package bot.handler

import bot.commands.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class CommandHandler {
  private Map<String, Command> commands = new HashMap<String, Command>()

  // implement constructor that takes a list of commands and adds them to the map
  CommandHandler(List<Command> commands) {
    commands.each { command -> addCommand(command) }
  }

  void addCommand(Command command) {
    commands.put(command.getName(), command)
  }

  Command getCommand(String command) {
    return commands.get(command)
  }

  List<Command> getCommands() {
    return commands.values().toList()
  }

  void executeCommand(String command, MessageReceivedEvent event) {
    def commandhandler = getCommandHandler(command)

    if (commandhandler == null) {
      event.getChannel().sendMessage("Command not found").queue()
      return
    }

    commandhandler.execute(event)
  }

  private Command getCommandHandler(String command) {
    if (commands.containsKey(command)) {
      return commands.get(command)
    } else {
      def defaultCommand = new DefaultCommand()
      return defaultCommand
    }
  }
}