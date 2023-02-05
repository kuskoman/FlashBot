package bot.commands.text.radio

import radio.RadioManager
import bot.commands.text.TextCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class BackupRadioTextCommand implements TextCommand {

    @Override
    String getName() {
        return 'backupradio'
    }

    @Override
    String getDescription() {
        return 'Backup the radios'
    }

    @Override
    String getUsage() {
        return 'backupradio'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def radioManager = new RadioManager()
        def backup = radioManager.getRadioBackup()
        def backupCodeBlock = "`${backup}`"
        event.getChannel().sendMessage("Radios backed up:\n${backupCodeBlock}").queue()
    }

}
