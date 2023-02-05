package bot.handler

import bot.commands.text.*
import bot.commands.text.radio.*

class TextTextCommandsHandlerFactory {

    private static TextTextCommandsHandler handler

    static TextTextCommandsHandler getHandler() {
        if (handler == null) {
            def commands = getTextCommands()
            handler = new TextTextCommandsHandler(commands)
        }

        return handler
    }

    private static List<TextCommand> getTextCommands() {
        return [
            new ListTextCommandsTextCommand(),
            new PingTextCommand(),
            new HelpTextCommand(),
            new AddRadioTextCommand(),
            new RemoveRadioTextCommand(),
            new ListRadioTextCommand(),
            new PlayRadioTextCommand(),
            new RemoveRadioTextCommand(),
            new BackupRadioTextCommand(),
            new RestoreRadioBackupTextCommand(),
        ]
    }

}
