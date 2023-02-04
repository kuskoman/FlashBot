package bot.commands.radio

import bot.commands.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import redis.RedisClientFactory

class ListRadioCommand implements Command {

    @Override
    String getName() {
        return 'listradio'
    }

    @Override
    String getDescription() {
        return 'List all radios'
    }

    @Override
    String getUsage() {
        return 'listradio'
    }

    @Override
    void execute(MessageReceivedEvent event, String args) {
        def redis = RedisClientFactory.getClient()
        def map = redis.getMap('radios')
        def radios = map.readAllMap();
        def message = 'Radios:\n'
        radios.each { radio ->
            message += "${radio.key} - ${radio.value}\n"
        }
        event.getChannel().sendMessage(message).queue()
    }

}
