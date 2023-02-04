package bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import bot.handler.CommandHandlerFactory

class HelpCommand implements Command {

    @Override
    String getName() {
        return 'help'
    }

    @Override
    String getDescription() {
        return 'Shows help for given command'
    }

    @Override
    String getUsage() {
        return 'help <command>'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def handler = CommandHandlerFactory.getHandler()
        def command = handler.getCommand(args)
        if (!command) {
            event.getChannel().sendMessage("You must provide a command.\nUsage: `${getUsage()}`").queue()
            return
        }

        def helpMessage = """Command: `${command.getName()}`
Description: ${command.getDescription()}
Usage: `${command.getUsage()}`
"""

        event.getChannel().sendMessage(helpMessage).queue()
    }

}
