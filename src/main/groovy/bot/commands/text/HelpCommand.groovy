package bot.commands.text

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import bot.handler.TextTextCommandsHandlerFactory

class HelpTextCommand implements TextCommand {

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
        def handler = TextTextCommandsHandlerFactory.getHandler()
        def command = handler.getTextCommand(args)
        if (!command) {
            event.getChannel().sendMessage("You must provide a command.\nUsage: `${getUsage()}`").queue()
            return
        }

        def helpMessage = """TextCommand: `${command.getName()}`
Description: ${command.getDescription()}
Usage: `${command.getUsage()}`
"""

        event.getChannel().sendMessage(helpMessage).queue()
    }

}
