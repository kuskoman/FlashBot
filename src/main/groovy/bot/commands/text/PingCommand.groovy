package bot.commands.text

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

public class PingTextCommand implements TextCommand {

    @Override
    public String getName() {
        return 'ping'
    }

    @Override
    public String getDescription() {
        return 'Pong!'
    }

    @Override
    public String getUsage() {
        return 'ping'
    }

    @Override
    public void execute(MessageReceivedEvent event, String args) {
        event.getChannel().sendMessage('Pong!').queue()
    }

}
