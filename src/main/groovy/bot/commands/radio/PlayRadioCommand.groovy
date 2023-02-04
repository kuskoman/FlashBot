package bot.commands.radio

import bot.commands.Command

import player.PlayerManager

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import redis.RedisClientFactory

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
        def redis = RedisClientFactory.getClient()
        def splittedArgs = args.split(' ')
        if (splittedArgs.size() != 1) {
            event.getChannel().sendMessage("Invalid arguments. Usage: `${getUsage()}`").queue()
            return
        }
        def radioName = splittedArgs[0]
        def map = redis.getMap('radios')
        def radioUrl = map.get(radioName)
        if (radioUrl == null) {
            event.getChannel().sendMessage('Radio not found').queue()
            return
        }

        PlayerManager.play(event, radioUrl)
        event.getChannel().sendMessage("Radio ${radioName} playin'").queue()
    }

}
