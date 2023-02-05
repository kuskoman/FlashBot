package bot.commands.text

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface TextCommand {

    String getName()
    String getDescription()
    String getUsage()
    void execute(MessageReceivedEvent event, String args)

}
