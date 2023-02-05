package bot.commands.text.radio

import radio.RadioManager
import bot.commands.text.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class BackupRadioCommand implements Command {

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
        def backup = radioManager.getRadioBackupJson()
        event.getChannel().sendMessage("Radios backed up:\n`${backup}`").queue()
    }

}
