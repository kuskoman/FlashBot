package bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class HelpCommand implements Command {
    @Override
    String getName() {
        return "help"
    }

    @Override
    String getDescription() {
        return "Shows help"
    }

    @Override
    String getUsage() {
        return "help"
    }

    @Override
    void execute(MessageReceivedEvent event) {
        def message = "Commands: "
        message += CommandHandlerFactory.getHandler().getCommands().collect { command -> command.getName() }.join(", ")
        event.getChannel().sendMessage(message).queue()
    }
}