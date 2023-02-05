package bot.commands.text.radio

import bot.commands.text.Command
import player.PlayerManager
import radio.RadioManager
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class PlayRadioCommand implements Command {

    @Override
    String getName() {
        return 'playradio'
    }

    @Override
    String getDescription() {
        return 'Play a radio'
    }

    @Override
    String getUsage() {
        return 'playradio <name>'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def splittedArgs = args.split(' ')
        if (splittedArgs.size() != 1) {
            event.getChannel().sendMessage("Invalid arguments. Usage: `${getUsage()}`").queue()
            return
        }
        def radioName = splittedArgs[0]
        def radioManager = new RadioManager()
        def radioUrl = radioManager.getRadioUrl(radioName)
        if (radioUrl == null) {
            event.getChannel().sendMessage('Radio not found').queue()
            return
        }

        def playerManager = PlayerManager.getInstance()
        playerManager.loadAndPlay(event, radioUrl)
        event.getChannel().sendMessage("Radio ${radioName} playin'").queue()
    }

}
