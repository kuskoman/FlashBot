package bot.handler

import bot.commands.*

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
            new HelpCommand()
        ]
    }
}