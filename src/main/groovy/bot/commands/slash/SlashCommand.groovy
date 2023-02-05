package bot.commands.slash

import net.dv8tion.jda.api.interactions.commands.Command

interface SlashCommand {

    String getName()
    String getDescription()
    Command setup()
    void execute()

}
