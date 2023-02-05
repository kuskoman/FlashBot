package bot.handler

import bot.commands.text.*
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class TextTextCommandsHandler {

    private Map<String, TextCommand> commands = new HashMap<String, TextCommand>()

    // implement constructor that takes a list of commands and adds them to the map
    TextTextCommandsHandler(List<TextCommand> commands) {
        commands.each { command -> addTextCommand(command) }
    }

    void addTextCommand(TextCommand command) {
        commands.put(command.getName(), command)
    }

    TextCommand getTextCommand(String command) {
        return commands.get(command)
    }

    List<TextCommand> getTextCommands() {
        return commands.values().toList()
    }

    void executeTextCommand(String command, MessageReceivedEvent event, String args) {
        def commandhandler = getTextTextCommandsHandler(command)

        if (commandhandler == null) {
            event.getChannel().sendMessage('TextCommand not found').queue()
            return
        }

        commandhandler.execute(event, args)
    }

    private TextCommand getTextTextCommandsHandler(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command)
        }
        def defaultTextCommand = new DefaultTextCommand()
        return defaultTextCommand
    }

}
