package bot.commands.text.radio

import bot.commands.text.Command
import radio.RadioManager
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class AddRadioCommand implements Command {

    @Override
    String getName() {
        return 'addradio'
    }

    @Override
    String getDescription() {
        return 'Add a radio to the list'
    }

    @Override
    String getUsage() {
        return 'addradio <name> <url>'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def radioManager = new RadioManager()
        def splittedArgs = args.split(' ')
        if (splittedArgs.size() != 2) {
            event.getChannel().sendMessage("Invalid arguments. Usage: `${getUsage()}`").queue()
            return
        }
        def radioName = splittedArgs[0]
        def radioUrl = splittedArgs[1]
        radioManager.addRadio(radioName, radioUrl)
        event.getChannel().sendMessage('Radio added').queue()
    }

}
