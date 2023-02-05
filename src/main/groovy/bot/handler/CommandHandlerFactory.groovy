package bot.handler

import bot.commands.text.*
import bot.commands.text.radio.*

class TextCommandsHandlerFactory {

    private static TextCommandsHandler handler

    static TextCommandsHandler getHandler() {
        if (handler == null) {
            def commands = getCommands()
            handler = new TextCommandsHandler(commands)
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
            new PlayRadioCommand(),
            new RemoveRadioCommand(),
            new BackupRadioCommand(),
        ]
    }

}
