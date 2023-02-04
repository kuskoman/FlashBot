package bot.commands.radio

import bot.commands.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import redis.RedisClientFactory

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
        def redis = RedisClientFactory.getClient()
        def splittedArgs = args.split(' ')
        if (splittedArgs.size() != 2) {
            event.getChannel().sendMessage("Invalid arguments. Usage: `${getUsage()}`").queue()
            return
        }
        def radioName = splittedArgs[0]
        def radioUrl = splittedArgs[1]
        def bucket = redis.getBucket('radios')
        bucket.set(radioName, radioUrl)
        event.getChannel().sendMessage('Radio added').queue()
    }
}
