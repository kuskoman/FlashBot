package bot.handler

import bot.commands.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class Handler {
  private Map<String, Command> commands = new HashMap<String, Command>()

  // implement constructor that takes a list of commands and adds them to the map
  Handler(List<Command> commands) {
    commands.each { command -> addCommand(command) }
  }

  void addCommand(Command command) {
    commands.put(command.getName(), command)
  }

  Command getCommand(String command) {
    return commands.get(command)
  }

  void executeCommand(String command, MessageReceivedEvent event) {
    def commandhandler = commands.get(command);
    if (commandHandler == null) {
      return
    }

    commandHandler.execute(event)
  }
}