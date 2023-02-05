package bot.commands.text

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import bot.handler.TextTextCommandsHandlerFactory

class ListTextCommandsTextCommand implements TextCommand {

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
        def handler = TextTextCommandsHandlerFactory.getHandler()
        def commands = handler.getTextCommands()
        def message = 'TextCommands: '
        message += commands.collect { command -> command.getName() }.join(', ')
        event.getChannel().sendMessage(message).queue()
    }

}
