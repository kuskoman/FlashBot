package bot.commands.slash.radio

import bot.commands.slash.SlashCommand
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.*

class PlayRadioCommand implements SlashCommand {

    @Override
    String getName() {
        return 'playradio'
    }

    @Override
    String getDescription() {
        return 'Play a radio'
    }

    Command setup() {
        return new Commands.CommandBuilder(getName(), getDescription())
            .addOption(OptionType.STRING, 'radio', 'The radio to play', true)
            .build()
    }

    @Override
    void execute(SlashCommandEvent event) {
        event.reply('Playing radio').queue()
    }

}
