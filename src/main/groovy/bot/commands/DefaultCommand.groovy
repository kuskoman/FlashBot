package bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class DefaultCommand implements Command {

    @Override
    String getName() {
        return 'default'
    }

    @Override
    String getDescription() {
        return 'Default command'
    }

    @Override
    String getUsage() {
        return 'default'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def message = 'Command not found. Use `list` command to list all commands'
        event.getChannel().sendMessage(message).queue()
    }

}
