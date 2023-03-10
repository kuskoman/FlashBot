package bot.commands.text.radio

import radio.RadioManager
import bot.commands.text.TextCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class RestoreRadioBackupTextCommand implements TextCommand {

    @Override
    String getName() {
        return 'restoreradiobackup'
    }

    @Override
    String getDescription() {
        return 'Restore the radios from a backup'
    }

    @Override
    String getUsage() {
        return 'restoreradiobackup <backup>'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def radioManager = new RadioManager()
        def backup = args
        radioManager.restoreRadioBackupJson(backup)
        event.getChannel().sendMessage('Radios restored').queue()
    }

}
