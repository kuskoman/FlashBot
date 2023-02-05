package bot.handler

import bot.commands.text.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class TextCommandsHandler {

    private Map<String, Command> commands = new HashMap<String, Command>()

    // implement constructor that takes a list of commands and adds them to the map
    TextCommandsHandler(List<Command> commands) {
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

    void executeCommand(String command, MessageReceivedEvent event, String args) {
        def commandhandler = getTextCommandsHandler(command)

        if (commandhandler == null) {
            event.getChannel().sendMessage('Command not found').queue()
            return
        }

        commandhandler.execute(event, args)
    }

    private Command getTextCommandsHandler(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command)
        }
        def defaultCommand = new DefaultCommand()
        return defaultCommand
    }

}
