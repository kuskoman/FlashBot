package bot.commands.text

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import bot.handler.TextCommandsHandlerFactory

class ListCommandsCommand implements Command {

    @Override
    String getName() {
        return 'list'
    }

    @Override
    String getDescription() {
        return 'Lists all commands'
    }

    @Override
    String getUsage() {
        return 'list'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def handler = TextCommandsHandlerFactory.getHandler()
        def commands = handler.getCommands()
        def message = 'Commands: '
        message += commands.collect { command -> command.getName() }.join(', ')
        event.getChannel().sendMessage(message).queue()
    }

}
