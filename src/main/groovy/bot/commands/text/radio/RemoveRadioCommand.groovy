package bot.commands.text.radio

import radio.RadioManager
import bot.commands.text.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class RemoveRadioCommand implements Command {

    @Override
    String getName() {
        return 'removeradio'
    }

    @Override
    String getDescription() {
        return 'Remove a radio from the list'
    }

    @Override
    String getUsage() {
        return 'removeradio <name>'
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
        radioManager.removeRadio(radioName)
        event.getChannel().sendMessage('Radio removed').queue()
    }

}
