package bot.handler

import bot.commands.slash.*

class SlashCommandsHandler {

    private Map<String, SlashCommand> commands = new HashMap<String, SlashCommand>()

    SlashCommandsHandler(List<SlashCommand> commands) {
        commands.each { command -> addSlashCommand(command) }
    }

}
