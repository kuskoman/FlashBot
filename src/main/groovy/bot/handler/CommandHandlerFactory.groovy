package bot.handler

import bot.commands.*
import bot.commands.radio.*

class CommandHandlerFactory {

    private static CommandHandler handler

    static CommandHandler getHandler() {
        if (handler == null) {
            def commands = getCommands()
            handler = new CommandHandler(commands)
        }

        return handler
    }

    private static List<Command> getCommands() {
        return [
            new ListCommandsCommand(),
            new PingCommand(),
            new HelpCommand(),
            new AddRadioCommand(),
            new RemoveRadioCommand(),
            new ListRadioCommand(),
        ]
    }

}
