package bot.handler

import bot.commands.*

class CommandHandlerFactory {
    private static Handler handler = new Handler()

    CommandHandlerFactory() {
        handler.addCommand(new ListCommandsCommand(handler))
        handler.addCommand(new PingCommand())
    }

    static Handler getHandler() {
        return handler
    }
}